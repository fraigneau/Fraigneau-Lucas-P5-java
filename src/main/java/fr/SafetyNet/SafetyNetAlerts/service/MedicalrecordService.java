package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class MedicalrecordService implements GenericService<MedicalRecord> {

    private List<MedicalRecord> medicalRecords;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.medicalRecords = jsonWrapper.getList(MedicalRecord.class);
    }

    @Override
    public List<MedicalRecord> readAll() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord readById(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        for (MedicalRecord medicalRecord : medicalRecords) { // firstname and lastname
            if (medicalRecord.getFirstName().equals(args[0]) && medicalRecord.getLastName().equals(args[1])) {
                return medicalRecord;
            }
        }
        return null;
    }

    @Override
    public MedicalRecord Create(MedicalRecord newMedicalRecord) {
        try {
            medicalRecords.add(newMedicalRecord);
            jsonWrapper.setList(MedicalRecord.class, medicalRecords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newMedicalRecord;
    }

    @Override
    public void deleteById(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        medicalRecords.remove(readById(args));
        try {
            jsonWrapper.setList(MedicalRecord.class, medicalRecords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public MedicalRecord update(MedicalRecord updatedMedicalRecord, String... args) {
        boolean found = false;
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(args[0]) &&
                    medicalRecord.getLastName().equals(args[1])) {
                int index = medicalRecords.indexOf(medicalRecord);
                medicalRecords.set(index, updatedMedicalRecord);
                found = true;
            }
        }
        if (!found)
            logger.error("{} not found", updatedMedicalRecord.getFirstName() +
                    " " + updatedMedicalRecord.getLastName());
        return updatedMedicalRecord;
    }

}
