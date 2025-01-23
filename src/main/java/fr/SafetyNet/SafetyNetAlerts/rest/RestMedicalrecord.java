package fr.SafetyNet.SafetyNetAlerts.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.service.DataService;

@RestController
public class RestMedicalrecord {

    private final DataService dataService;

    public RestMedicalrecord(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecord> getAllMedicalRecords() {
        return dataService.getMedicalrecords();
    }

}
