package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.exception.ConflictException;
import fr.SafetyNet.SafetyNetAlerts.exception.ResourceNotFoundException;
import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

/**
 * Service for managing Medicalrecord entities.
 */
@Service
public class MedicalrecordService implements GenericService<Medicalrecord> {

    private List<Medicalrecord> medicalRecords;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

    /**
     * Default constructor.
     */
    public MedicalrecordService() {
    }

    /**
     * Constructs a new MedicalrecordService with the specified JsonDataRepository.
     *
     * @param jsonWrapper the repository to manage JSON data
     * @throws IOException if an I/O error occurs
     */
    @Autowired
    public MedicalrecordService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.medicalRecords = jsonWrapper.getList(Medicalrecord.class);
    }

    /**
     * Retrieves all medical records.
     *
     * @return a list of all medical records
     */
    @Override
    public List<Medicalrecord> readAll() {
        return medicalRecords;
    }

    /**
     * Retrieves a medical record by first and last name.
     *
     * @param args the first and last name of the person
     * @return the medical record of the person with the specified first and last
     *         name
     * @throws IllegalArgumentException  if the number of arguments is not 2
     * @throws ResourceNotFoundException if the medical record is not found
     */
    @Override
    public Medicalrecord readById(String... args) {
        if (args.length != 2) {
            logger.error("Expected 2 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        for (Medicalrecord medicalRecord : medicalRecords) { // firstname and lastname
            if (medicalRecord.getFirstName().equals(args[0]) && medicalRecord.getLastName().equals(args[1])) {
                logger.info("MedicalRecord found");
                return medicalRecord;
            }
        }
        logger.error("MedicalRecord not found");
        throw new ResourceNotFoundException("MedicalRecord not found -> " + args[0] + ", " + args[1]);
    }

    /**
     * Creates a new medical record.
     *
     * @param newMedicalRecord the medical record to create
     * @return the created medical record
     * @throws ConflictException if the medical record already exists
     */
    @Override
    public Medicalrecord create(Medicalrecord newMedicalRecord) {

        for (Medicalrecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(newMedicalRecord.getFirstName())
                    && medicalRecord.getLastName().equals(newMedicalRecord.getLastName())) {
                logger.warn("MedicalRecord already exists: {} {}",
                        newMedicalRecord.getFirstName(), newMedicalRecord.getLastName());
                throw new ConflictException("MedicalRecord already exists");
            }
        }

        try {
            medicalRecords.add(newMedicalRecord);
            jsonWrapper.setList(Medicalrecord.class, medicalRecords);
            logger.info("MedicalRecord {} {} created successfully", newMedicalRecord.getFirstName(),
                    newMedicalRecord.getLastName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newMedicalRecord;
    }

    /**
     * Deletes a medical record by first and last name.
     *
     * @param args the first and last name of the person
     * @throws IllegalArgumentException if the number of arguments is not 2
     */
    @Override
    public void deleteById(String... args) {
        if (args.length != 2) {
            logger.error("Expected 2 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        medicalRecords.remove(readById(args));
        try {
            jsonWrapper.setList(Medicalrecord.class, medicalRecords);
            logger.info("MedicalRecord {} {} deleted successfully", args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing medical record.
     *
     * @param updatedMedicalRecord the updated medical record data
     * @param args                 the first and last name of the person
     * @return the updated medical record
     * @throws ResourceNotFoundException if the medical record is not found
     */
    @Override
    public Medicalrecord update(Medicalrecord updatedMedicalRecord, String... args) {
        boolean found = false;
        for (Medicalrecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(args[0]) &&
                    medicalRecord.getLastName().equals(args[1])) {
                int index = medicalRecords.indexOf(medicalRecord);
                medicalRecords.set(index, updatedMedicalRecord);
                found = true;
            }
        }
        if (!found) {
            logger.error("{} not found", updatedMedicalRecord.getFirstName() +
                    " " + updatedMedicalRecord.getLastName());
            throw new ResourceNotFoundException("MedicalRecord not found -> " + args[0] + ", " + args[1]);
        }
        logger.info("MedicalRecord {} {} updated successfully", updatedMedicalRecord.getFirstName(),
                updatedMedicalRecord.getLastName());
        return updatedMedicalRecord;
    }

}
