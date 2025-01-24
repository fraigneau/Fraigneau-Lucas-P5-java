package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.DAO.JsonWrapper;
import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;

@Service
public class MedicalrecordService {

    private List<MedicalRecord> medicalRecords;

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonWrapper jsonWrapper) {
        this.medicalRecords = jsonWrapper.getMedicalrecords();
    }

    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalRecords;
    }

}
