package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;

@Service
public class MedicalrecordService {

    private List<MedicalRecord> medicalRecords;

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonService jsonService) {
        this.medicalRecords = jsonService.getMedicalrecords();
    }

    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalRecords;
    }

}
