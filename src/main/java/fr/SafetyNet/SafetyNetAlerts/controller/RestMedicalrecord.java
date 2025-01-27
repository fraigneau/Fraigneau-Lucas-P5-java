package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;

@RestController
public class RestMedicalrecord {

    private final MedicalrecordService medicalrecordService;

    public RestMedicalrecord(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }

    @GetMapping("/medicalrecord")
    public List<MedicalRecord> readAll() {
        return medicalrecordService.readAll();
    }

    @GetMapping("medicalrecord/{firstName}.{lastName}")
    public MedicalRecord readById(@PathVariable String firstName, @PathVariable String lastName) {
        return medicalrecordService.readById(firstName, lastName);
    }

    @PostMapping("/medicalrecord")
    public MedicalRecord create(@RequestBody MedicalRecord medicalRecord) {
        medicalrecordService.Create(medicalRecord);
        return medicalRecord;
    }

    @DeleteMapping("/medicalrecord/{firstName}.{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.deleteById(firstName, lastName);
    }

    @PutMapping("/medicalrecord/{firstName}.{lastName}")
    public MedicalRecord update(@RequestBody MedicalRecord updatedPerson,
            @PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.update(updatedPerson, firstName, lastName);
        return updatedPerson;
    }

}
