package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.repositories.JsonWrapper;

@Service
public class MedicalrecordService {

    private List<MedicalRecord> medicalRecords;

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonWrapper jsonWrapper) throws IOException {
        this.medicalRecords = jsonWrapper.getList(MedicalRecord.class);
    }

    public List<MedicalRecord> getMedicalrecordsList() {
        return medicalRecords;
    }

}
