package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.service.FirestationService;

/**
 * Rest controller for managing Firestation entities.
 */
@RestController
public class RestFirestation {

    private final FirestationService firestationService;

    /**
     * Constructs a new RestFirestation with the specified FirestationService.
     *
     * @param firestationService the service to manage firestations
     */
    public RestFirestation(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    /**
     * Retrieves all firestations.
     *
     * @return a list of all firestations
     */
    @GetMapping("/firestation")
    public List<Firestation> readAll() {
        return firestationService.readAll();
    }

    /**
     * Retrieves a firestation by its address.
     *
     * @param adress the address of the firestation to retrieve
     * @return the firestation with the specified address
     */
    @GetMapping("firestation/{adress}")
    public Firestation readById(@PathVariable String adress) {
        return firestationService.readById(adress);
    }

    /**
     * Creates a new firestation.
     *
     * @param fireStation the firestation to create
     * @return a message indicating the firestation was created
     */
    @PostMapping("/firestation")
    public String create(@RequestBody Firestation fireStation) {
        firestationService.create(fireStation);
        return "Firestation created at address: " + fireStation.getAddress();
    }

    /**
     * Deletes a firestation by its address.
     *
     * @param adress the address of the firestation to delete
     * @return a message indicating the firestation was deleted
     */
    @DeleteMapping("/firestation/{adress}")
    public String delete(@PathVariable String adress) {
        firestationService.deleteById(adress);
        return "Firestation deleted at address: " + adress;
    }

    /**
     * Updates an existing firestation.
     *
     * @param updatedFireStation the updated firestation data
     * @param adress             the address of the firestation to update
     * @return a message indicating the firestation was updated
     */
    @PutMapping("/firestation/{adress}")
    public String update(@RequestBody Firestation updatedFireStation,
            @PathVariable String adress) {
        firestationService.update(updatedFireStation, adress);
        return "Firestation updated at address: " + updatedFireStation.getAddress();
    }

}
