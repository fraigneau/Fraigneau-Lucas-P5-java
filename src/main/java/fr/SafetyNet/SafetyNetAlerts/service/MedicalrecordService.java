package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class MedicalrecordService implements CrudService<MedicalRecord> {

    private List<MedicalRecord> medicalRecords;
    private JsonDataRepository jsonWrapper;

    public MedicalrecordService() {
    }

    @Autowired
    public MedicalrecordService(JsonDataRepository jsonWrapper) throws IOException {
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
    public MedicalRecord Create(MedicalRecord newMedicalRecord) {
        try {
            medicalRecords.add(newMedicalRecord);
            jsonWrapper.setList(MedicalRecord.class, medicalRecords);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newMedicalRecord;
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
