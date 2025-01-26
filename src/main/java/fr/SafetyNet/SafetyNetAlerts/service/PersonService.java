package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonWrapper;

@Service
public class PersonService implements CrudService<Person> {

    private List<Person> persons;

    public PersonService() {
    }

    @Autowired
    public PersonService(JsonWrapper jsonWrapper) throws IOException {
        this.persons = jsonWrapper.getList(Person.class);
    }

    @Override
    public List<Person> getAll() {
        return persons;
    }

    @Override
    public Person findById(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }
        String firstName = args[0];
        String lastName = args[1];

        for (Person person : persons) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person Create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CreateById'");
    }

    @Override
    public void deleteById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Person update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
