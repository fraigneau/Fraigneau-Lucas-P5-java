package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.wrapper.DataWrapper;
import jakarta.annotation.PostConstruct;

@Service
public class DataService {

    private DataWrapper dataWrapper;

    public DataService() {
    }

    @PostConstruct
    public void loadPerson() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data.json");

        if (inputStream == null) {
            throw new RuntimeException("Fichier 'data.json' introuvable !");
        }

        try {
            dataWrapper = objectMapper.readValue(inputStream, DataWrapper.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la désérialisation du fichier JSON", e);
        }
    }

    public List<Person> getPersons() {
        return dataWrapper.getPersons();
    }

    public List<FireStation> getFirestations() {
        return dataWrapper.getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords() {
        return dataWrapper.getMedicalrecords();
    }

}
