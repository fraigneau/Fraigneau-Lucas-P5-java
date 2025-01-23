package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;

@Service
public class PersonService {

    private List<Person> persons;

    public PersonService() {
    }

    @Autowired
    public PersonService(JsonService jsonService) {
        this.persons = jsonService.getPersons();
    }

    public List<Person> getPersons() {
        return persons;
    }

}
