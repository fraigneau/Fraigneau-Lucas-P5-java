package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;

@Service
public class FirestationService {

    private List<FireStation> fireStations;

    public FirestationService() {
    }

    @Autowired
    public FirestationService(JsonService jsonService) {
        this.fireStations = jsonService.getFirestations();
    }

    public List<FireStation> getFirestationsList() {
        return fireStations;
    }
}
