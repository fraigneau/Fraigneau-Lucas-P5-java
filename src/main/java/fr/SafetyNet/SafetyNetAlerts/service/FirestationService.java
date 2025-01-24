package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.DAO.JsonWrapper;
import fr.SafetyNet.SafetyNetAlerts.model.FireStation;

@Service
public class FirestationService {

    private List<FireStation> fireStations;

    public FirestationService() {
    }

    @Autowired
    public FirestationService(JsonWrapper jsonWrapper) {
        this.fireStations = jsonWrapper.getFirestations();
    }

    public List<FireStation> getFirestationsList() {
        return fireStations;
    }
}
