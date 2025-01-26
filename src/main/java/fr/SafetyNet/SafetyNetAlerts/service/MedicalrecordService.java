package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonWrapper;

@Service
public class MedicalrecordService implements CrudService<MedicalRecord> {

    private List<MedicalRecord> medicalRecords;

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonWrapper jsonWrapper) throws IOException {
        this.medicalRecords = jsonWrapper.getList(MedicalRecord.class);
    }

    @Override
    public List<MedicalRecord> getAll() {
        return medicalRecords;
    }

    @Override
    public MedicalRecord findById(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public MedicalRecord Create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Create'");
    }

    @Override
    public void deleteById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public MedicalRecord update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
