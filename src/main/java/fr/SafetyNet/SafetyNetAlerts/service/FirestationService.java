package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class FirestationService implements GenericService<FireStation> {

    private List<FireStation> fireStations;
    private JsonDataRepository jsonWrapper;

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
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        for (FireStation fireStation : fireStations) { // firstname and lastname
            if (fireStation.getAddress().equals(args[0])) {
                return fireStation;
            }
        }
        return null;
    }

    @Override
    public FireStation Create(FireStation newFireStation) {
        try {
            fireStations.add(newFireStation);
            jsonWrapper.setList(FireStation.class, fireStations);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newFireStation;
    }

    @Override
    public void deleteById(String... args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        fireStations.remove(readById(args));
        try {
            jsonWrapper.setList(FireStation.class, fireStations);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public FireStation update(FireStation updatedFireStation, String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
