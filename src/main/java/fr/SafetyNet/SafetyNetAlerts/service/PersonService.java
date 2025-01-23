package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class PersonService implements CrudService<Person> {

    private List<Person> persons;
    private JsonDataRepository jsonWrapper;

    public PersonService() {
    }

    @Autowired
    public PersonService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
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

        for (Person person : persons) { // firstname and lastname
            if (person.getFirstName().equals(args[0]) && person.getLastName().equals(args[1])) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person Create(Person newPerson) {
        try {
            persons.add(newPerson);
            jsonWrapper.setList(Person.class, persons);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newPerson;
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
