package fr.SafetyNet.SafetyNetAlerts.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.service.DataService;

@RestController
public class RestFirestation {

    private final DataService dataService;

    public RestFirestation(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/firestations")
    public List<FireStation> getAllFirestations() {
        return dataService.getFirestations();
    }

}
