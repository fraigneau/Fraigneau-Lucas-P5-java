package fr.SafetyNet.SafetyNetAlerts.DAO;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class JsonWrapper {

    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecord> medicalrecords;

    @PostConstruct
    public void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/data.json");

        if (inputStream == null) {
            throw new RuntimeException("Fichier 'data.json' introuvable !");
        }

        try {
            JsonWrapper data = objectMapper.readValue(inputStream, JsonWrapper.class);
            this.persons = data.getPersons();
            this.firestations = data.getFirestations();
            this.medicalrecords = data.getMedicalrecords();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la désérialisation du fichier JSON", e);
        }
    }

    // Getters et Setters
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}