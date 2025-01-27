package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class PersonService implements RepositoryService<Person> {

    private List<Person> persons;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService() {
    }

    @Autowired
    public PersonService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.persons = jsonWrapper.getList(Person.class);
    }

    @Override
    public List<Person> readAll() {
        logger.info("PersonService loaded successfully");
        return persons;
    }

    @Override
    public Person readById(String... args) {
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
    public void deleteById(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        persons.remove(readById(args));
        try {
            jsonWrapper.setList(Person.class, persons);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Person update(Person updatedPerson, String... args) {
        boolean found = false;
        for (Person person : persons) {
            if (person.getFirstName().equals(args[0]) &&
                    person.getLastName().equals(args[1])) {
                int index = persons.indexOf(person);
                persons.set(index, updatedPerson);
                found = true;
            }
        }
        if (!found)
            logger.error("{} not found", updatedPerson.getFirstName() +
                    " " + updatedPerson.getLastName());
        return updatedPerson;
    }

}
