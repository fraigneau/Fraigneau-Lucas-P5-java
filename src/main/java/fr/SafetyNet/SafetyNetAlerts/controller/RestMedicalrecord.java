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

@RestController
public class RestMedicalrecord {

    private final MedicalrecordService medicalrecordService;

    public RestMedicalrecord(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }

    @GetMapping("/medicalrecord")
    public List<Medicalrecord> readAll() {
        return medicalrecordService.readAll();
    }

    @GetMapping("medicalrecord/{firstName}.{lastName}")
    public Medicalrecord readById(@PathVariable String firstName, @PathVariable String lastName) {
        return medicalrecordService.readById(firstName, lastName);
    }

    @PostMapping("/medicalrecord")
    public String create(@RequestBody Medicalrecord medicalRecord) {
        medicalrecordService.create(medicalRecord);
        return "Medicalrecord created " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName();
    }

    @DeleteMapping("/medicalrecord/{firstName}.{lastName}")
    public String delete(@PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.deleteById(firstName, lastName);
        return "Medicalrecord deleted " + firstName + " " + lastName;
    }

    @PutMapping("/medicalrecord/{firstName}.{lastName}")
    public String update(@RequestBody Medicalrecord updatedRecord,
            @PathVariable String firstName, @PathVariable String lastName) {
        medicalrecordService.update(updatedRecord, firstName, lastName);
        return "Medicalrecord updated " + updatedRecord.getFirstName() + " " + updatedRecord.getLastName();
    }
}
