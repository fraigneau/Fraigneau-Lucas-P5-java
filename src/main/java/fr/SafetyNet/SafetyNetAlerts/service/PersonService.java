package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repositories.JsonWrapper;

@Service
public class PersonService {

    private List<Person> persons;

    public PersonService() {
    }

    @Autowired
    public PersonService(JsonWrapper jsonWrapper) throws IOException {
        this.persons = jsonWrapper.getList(Person.class);
    }

    public List<Person> getPersonsList() {
        return persons;
    }

    public Person findPersonByName(String firstName, String lastName) {
        for (Person person : persons) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

}
