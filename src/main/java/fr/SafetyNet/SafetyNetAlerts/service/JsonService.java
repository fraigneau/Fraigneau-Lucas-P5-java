package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.wrapper.JsonWrapper;
import jakarta.annotation.PostConstruct;

@Service
public class JsonService {

    private JsonWrapper JsonWrapper;

    public JsonService() {
    }

    @PostConstruct
    public void loadPerson() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data.json");

        if (inputStream == null) {
            throw new RuntimeException("Fichier 'data.json' introuvable !");
        }

        try {
            JsonWrapper = objectMapper.readValue(inputStream, JsonWrapper.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la désérialisation du fichier JSON", e);
        }
    }

    public List<Person> getPersons() {
        return JsonWrapper.getPersons();
    }

    public List<FireStation> getFirestations() {
        return JsonWrapper.getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords() {
        return JsonWrapper.getMedicalrecords();
    }

}
