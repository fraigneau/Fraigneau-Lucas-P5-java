package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.exception.ConflictException;
import fr.SafetyNet.SafetyNetAlerts.exception.ResourceNotFoundException;
import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

/**
 * Service for managing Firestation entities.
 */
@Service
public class FirestationService implements GenericService<Firestation> {

    private List<Firestation> fireStations;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(FirestationService.class);

    /**
     * Default constructor.
     */
    public FirestationService() {
    }

    /**
     * Constructs a new FirestationService with the specified JsonDataRepository.
     *
     * @param jsonWrapper the repository to manage JSON data
     * @throws IOException if an I/O error occurs
     */
    @Autowired
    public FirestationService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.fireStations = jsonWrapper.getList(Firestation.class);
    }

    /**
     * Retrieves all firestations.
     *
     * @return a list of all firestations
     */
    @Override
    public List<Firestation> readAll() {
        return fireStations;
    }

    /**
     * Retrieves a firestation by its address.
     *
     * @param args the address of the firestation to retrieve
     * @return the firestation with the specified address
     * @throws IllegalArgumentException  if the number of arguments is not 1
     * @throws ResourceNotFoundException if the firestation is not found
     */
    @Override
    public Firestation readById(String... args) {
        if (args.length != 1) {
            logger.error("Expected 1 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 1 arguments, got " + args.length);
        }

        for (Firestation fireStation : fireStations) {
            if (fireStation.getAddress().equals(args[0])) {
                logger.info("FireStation found");
                return fireStation;
            }
        }
        logger.error("FireStation not found");
        throw new ResourceNotFoundException("FireStation not found -> " + args[0]);
    }

    /**
     * Creates a new firestation.
     *
     * @param newFireStation the firestation to create
     * @return the created firestation
     * @throws ConflictException if the firestation already exists
     */
    @Override
    public Firestation create(Firestation newFireStation) {

        for (Firestation fireStation : fireStations) {
            if (fireStation.getAddress().equals(newFireStation.getAddress())) {
                logger.warn("FireStation already exist : {} {}",
                        newFireStation.getAddress());
                throw new ConflictException("FireStation already exist");
            }
        }

        try {
            fireStations.add(newFireStation);
            jsonWrapper.setList(Firestation.class, fireStations);
            logger.info("FireStation {} created successfully", newFireStation.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFireStation;
    }

    /**
     * Deletes a firestation by its address.
     *
     * @param args the address of the firestation to delete
     * @throws IllegalArgumentException if the number of arguments is not 1
     */
    @Override
    public void deleteById(String... args) {
        if (args.length != 1) {
            logger.error("Expected 1 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 1 arguments, got " + args.length);
        }

        fireStations.remove(readById(args));
        try {
            jsonWrapper.setList(Firestation.class, fireStations);
            logger.info("FireStation {} deleted successfully", args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing firestation.
     *
     * @param updatedFireStation the updated firestation data
     * @param args               the address of the firestation to update
     * @return the updated firestation
     * @throws ResourceNotFoundException if the firestation is not found
     */
    @Override
    public Firestation update(Firestation updatedFireStation, String... args) {
        boolean found = false;
        for (Firestation fireStation : fireStations) {
            if (fireStation.getAddress().equals(args[0])) {
                int index = fireStations.indexOf(fireStation);
                fireStations.set(index, updatedFireStation);
                found = true;
            }
        }
        if (!found) {
            logger.error("{} not found", updatedFireStation.getAddress());
            throw new ResourceNotFoundException("FireStation not found -> " + args[0]);
        }
        logger.info("FireStation {} updated successfully", updatedFireStation.getAddress());
        return updatedFireStation;
    }

}
