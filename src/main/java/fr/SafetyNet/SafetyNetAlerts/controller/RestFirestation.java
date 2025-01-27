package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<FireStation> getFirestationsList() {
        return firestationService.readAll();
    }

    @GetMapping("firestation/{adress}")
    public FireStation getOneMedicalrecord(@PathVariable String adress) {
        return firestationService.readById(adress);
    }

    @PostMapping("/firestation")
    public FireStation postMedicalrecord(@RequestBody FireStation fireStation) {
        firestationService.Create(fireStation);
        return fireStation;
    }

}
