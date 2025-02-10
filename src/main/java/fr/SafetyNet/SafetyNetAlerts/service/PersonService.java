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

/**
 * Service for managing Person entities.
 */
@Service
public class PersonService implements GenericService<Person> {

    private List<Person> persons;
    private JsonDataRepository jsonWrapper;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    /**
     * Default constructor.
     */
    public PersonService() {
    }

    /**
     * Constructs a new PersonService with the specified JsonDataRepository.
     *
     * @param jsonWrapper the repository to manage JSON data
     * @throws IOException if an I/O error occurs
     */
    @Autowired
    public PersonService(JsonDataRepository jsonWrapper) throws IOException {
        this.jsonWrapper = jsonWrapper;
        this.persons = jsonWrapper.getList(Person.class);
    }

    /**
     * Retrieves all persons.
     *
     * @return a list of all persons
     */
    @Override
    public List<Person> readAll() {
        logger.info("PersonService loaded successfully");
        return persons;
    }

    /**
     * Retrieves a person by first and last name.
     *
     * @param args the first and last name of the person
     * @return the person with the specified first and last name
     * @throws IllegalArgumentException  if the number of arguments is not 2
     * @throws ResourceNotFoundException if the person is not found
     */
    @Override
    public Person readById(String... args) {
        if (args.length != 2) {
            logger.error("Expected 2 arguments, got {}", args.length);
            throw new IllegalArgumentException("Expected 2 arguments, got " + args.length);
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

    /**
     * Creates a new person.
     *
     * @param newPerson the person to create
     * @return the created person
     * @throws ConflictException if the person already exists
     */
    @Override
    public Person create(Person newPerson) {

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

    /**
     * Deletes a person by first and last name.
     *
     * @param args the first and last name of the person
     * @throws IllegalArgumentException if the number of arguments is not 2
     */
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

    /**
     * Updates an existing person.
     *
     * @param updatedPerson the updated person data
     * @param args          the first and last name of the person
     * @return the updated person
     * @throws ResourceNotFoundException if the person is not found
     */
    @Override
    public Person update(Person updatedPerson, String... args) {
        boolean found = false;

        for (Person person : persons) {
            if (person.getFirstName().equals(args[0]) && person.getLastName().equals(args[1])) {
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
