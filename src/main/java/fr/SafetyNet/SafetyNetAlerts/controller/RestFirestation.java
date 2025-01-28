package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<FireStation> readAll() {
        return firestationService.readAll();
    }

    @GetMapping("firestation/{adress}")
    public FireStation readById(@PathVariable String adress) {
        return firestationService.readById(adress);
    }

    @PostMapping("/firestation")
    public String create(@RequestBody FireStation fireStation) {
        firestationService.Create(fireStation);
        return "Firestation created at address: " + fireStation.getAddress();
    }

    @DeleteMapping("/firestation/{adress}")
    public String delete(@PathVariable String adress) {
        firestationService.deleteById(adress);
        return "Firestation deleted at address: " + adress;
    }

    @PutMapping("/firestation/{adress}")
    public String update(@RequestBody FireStation updatedFireStation,
            @PathVariable String adress) {
        firestationService.update(updatedFireStation, adress);
        return "Firestation updated at address: " + updatedFireStation.getAddress();
    }

}
