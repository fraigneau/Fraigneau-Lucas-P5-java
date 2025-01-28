package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class FirestationService implements GenericService<FireStation> {

    private List<FireStation> fireStations;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(FirestationService.class);

    public FirestationService() {
    }

    @Autowired
    public FirestationService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.fireStations = jsonWrapper.getList(FireStation.class);
    }

    @Override
    public List<FireStation> readAll() {
        return fireStations;
    }

    @Override
    public FireStation readById(String... args) {
        if (args.length != 1) {
            logger.error("Expected 1 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        for (FireStation fireStation : fireStations) { // firstname and lastname
            if (fireStation.getAddress().equals(args[0])) {
                logger.info("FireStation found");
                return fireStation;
            }
        }
        logger.error("FireStation not found");
        return null;
    }

    @Override
    public FireStation Create(FireStation newFireStation) {
        try {
            fireStations.add(newFireStation);
            jsonWrapper.setList(FireStation.class, fireStations);
            logger.info("FireStation {} created successfully", newFireStation.getAddress());
        } catch (IOException e) {
            logger.error("Error creating FireStation");
            e.printStackTrace();
        }
        return newFireStation;
    }

    @Override
    public void deleteById(String... args) {
        if (args.length != 1) {
            logger.error("Expected 1 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 1 arguments, got " + args.length);
        }

        fireStations.remove(readById(args));
        try {
            jsonWrapper.setList(FireStation.class, fireStations);
            logger.info("FireStation {} deleted successfully", args[0]);
        } catch (IOException e) {
            logger.error("Error deleting FireStation");
            e.printStackTrace();
        }
    }

    @Override
    public FireStation update(FireStation updatedFireStation, String... args) {
        boolean found = false;
        for (FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(args[0])) {
                int index = fireStations.indexOf(fireStation);
                fireStations.set(index, updatedFireStation);
                found = true;
            }
        }
        if (!found)
            logger.error("{} not found", updatedFireStation.getAddress());
        logger.info("FireStation {} updated successfully", updatedFireStation.getAddress());
        return updatedFireStation;
    }

}
