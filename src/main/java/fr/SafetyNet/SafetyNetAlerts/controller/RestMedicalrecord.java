package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;

@RestController
public class RestMedicalrecord {

    private final MedicalrecordService medicalrecordService;

    public RestMedicalrecord(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecord> getAllMedicalRecordsList() {
        return medicalrecordService.getAll();
    }

}
