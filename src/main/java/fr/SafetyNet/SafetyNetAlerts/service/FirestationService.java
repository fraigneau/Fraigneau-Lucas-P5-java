package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.repositories.JsonWrapper;

@Service
public class FirestationService implements CrudService<FireStation> {

    private List<FireStation> fireStations;

    public FirestationService() {
    }

    @Autowired
    public FirestationService(JsonWrapper jsonWrapper) throws IOException {
        this.fireStations = jsonWrapper.getList(FireStation.class);
    }

    @Override
    public List<FireStation> getAll() {
        return fireStations;
    }

    @Override
    public FireStation findById(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public FireStation Create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Create'");
    }

    @Override
    public void deleteById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public FireStation update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
