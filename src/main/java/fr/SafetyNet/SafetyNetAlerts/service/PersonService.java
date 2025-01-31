package fr.SafetyNet.SafetyNetAlerts.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.SafetyNet.SafetyNetAlerts.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@Service
public class PersonService implements GenericService<Person> {

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
            logger.error("Expected 2 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " +
                    args.length);
        }

        for (Person person : persons) { // firstname and lastname
            if (person.getFirstName().equals(args[0]) && person.getLastName().equals(args[1])) {
                logger.info("Person found");
                return person;
            }
        }
        logger.error("Person not found");
        throw new ResourceNotFoundException("Person not found -> " + args[0] + ", " + args[1]);
    }

    @Override
    public Person Create(Person newPerson) {

        for (Person person : persons) {
            if (person.getFirstName().equals(newPerson.getFirstName()) && person.getLastName().equals(
                    newPerson.getLastName())) {
                logger.warn("Person already exist : {} {}", newPerson.getFirstName(), newPerson.getLastName());
                throw new ConflictException("Person already exist");
            }
        }

        try {
            persons.add(newPerson);
            jsonWrapper.setList(Person.class, persons);
            logger.info("Person {} {} created successfully", newPerson.getFirstName(), newPerson.getLastName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPerson;
    }

    @Override
    public void deleteById(String... args) {
        if (args.length != 2) {
            logger.error("Expected 2 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
        }

        persons.remove(readById(args));
        try {
            jsonWrapper.setList(Person.class, persons);
            logger.info("Person {} {} deleted successfully", args[0], args[1]);
        } catch (IOException e) {
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

        if (!found) {
            logger.error("{} not found", updatedPerson.getFirstName() +
                    " " + updatedPerson.getLastName());
            throw new ResourceNotFoundException("Person not found -> " + args[0] + ", " + args[1]);
        }
        logger.info("Person {} {} updated successfully", updatedPerson.getFirstName(),
                updatedPerson.getLastName());
        return updatedPerson;
    }

}
