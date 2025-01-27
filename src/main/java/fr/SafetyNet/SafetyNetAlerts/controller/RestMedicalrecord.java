package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<MedicalRecord> getMedicalRecordsList() {
        return medicalrecordService.readAll();
    }

    @GetMapping("medicalrecord/{firstName}.{lastName}")
    public MedicalRecord getOneMedicalrecord(@PathVariable String firstName, @PathVariable String lastName) {
        return medicalrecordService.readById(firstName, lastName);
    }

    @PostMapping("/medicalrecord")
    public MedicalRecord postMedicalrecord(@RequestBody MedicalRecord medicalRecord) {
        medicalrecordService.Create(medicalRecord);
        return medicalRecord;
    }

}
