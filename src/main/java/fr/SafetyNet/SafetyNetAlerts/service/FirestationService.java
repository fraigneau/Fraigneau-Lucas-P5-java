package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.repositories.JsonWrapper;

@Service
public class FirestationService {

    private List<FireStation> fireStations;

    public FirestationService() {
    }

    @Autowired
    public FirestationService(JsonWrapper jsonWrapper) throws IOException {
        this.fireStations = jsonWrapper.getList(FireStation.class);
    }

    public List<FireStation> getFirestationsList() {
        return fireStations;
    }
}
