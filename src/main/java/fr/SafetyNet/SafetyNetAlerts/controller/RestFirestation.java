package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.service.FirestationService;

@RestController
public class RestFirestation {

    private final FirestationService firestationService;

    public RestFirestation(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestation")
    public List<FireStation> getAllFirestationsList() {
        return firestationService.getAll();
    }

}
