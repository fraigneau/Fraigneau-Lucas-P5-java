package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;

/**
 * Rest controller for managing Medicalrecord entities.
 */
@RestController
public class RestMedicalrecord {

    private final MedicalrecordService medicalrecordService;

    /**
     * Constructs a new RestMedicalrecord with the specified MedicalrecordService.
     *
     * @param medicalrecordService the service to manage medical records
     */
    public RestMedicalrecord(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }

    /**
     * Retrieves all medical records.
     *
     * @return a list of all medical records
     */
    @GetMapping("/medicalrecord")
    public List<Medicalrecord> readAll() {
        return medicalrecordService.readAll();
    }

    /**
     * Retrieves a medical record by the first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @return the medical record of the person with the specified first and last
     *         name
     */
    @GetMapping("medicalrecord/{firstName}.{lastName}")
    public Medicalrecord readById(@PathVariable String firstName, @PathVariable String lastName) {
        return medicalrecordService.readById(firstName, lastName);
    }

    /**
     * Creates a new medical record.
     *
     * @param medicalRecord the medical record to create
     * @return a message indicating the medical record was created
     */
    @PostMapping("/medicalrecord")
    public String create(@RequestBody Medicalrecord medicalRecord) {
        medicalrecordService.create(medicalRecord);
        return "Medicalrecord created " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName();
    }

    /**
     * Deletes a medical record by the first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @return a message indicating the medical record was deleted
     */
    @DeleteMapping("/medicalrecord/{firstName}.{lastName}")
    public String delete(@PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.deleteById(firstName, lastName);
        return "Medicalrecord deleted " + firstName + " " + lastName;
    }

    /**
     * Updates an existing medical record.
     *
     * @param updatedRecord the updated medical record data
     * @param firstName     the first name of the person
     * @param lastName      the last name of the person
     * @return a message indicating the medical record was updated
     */
    @PutMapping("/medicalrecord/{firstName}.{lastName}")
    public String update(@RequestBody Medicalrecord updatedRecord,
            @PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.update(updatedRecord, firstName, lastName);
        return "Medicalrecord updated " + updatedRecord.getFirstName() + " " + updatedRecord.getLastName();
    }
}
