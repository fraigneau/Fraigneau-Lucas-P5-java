package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.service.PersonService;

/**
 * Rest controller for managing Person entities.
 */
@RestController
public class RestPerson {

    private final PersonService personService;

    /**
     * Constructs a new RestPerson with the specified PersonService.
     *
     * @param personService the service to manage persons
     */
    public RestPerson(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Retrieves all persons.
     *
     * @return a list of all persons
     */
    @GetMapping("/person")
    public List<Person> readAll() {
        return personService.readAll();
    }

    /**
     * Retrieves a person by their first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @return the person with the specified first and last name
     */
    @GetMapping("person/{firstName}.{lastName}")
    public Person readById(@PathVariable String firstName, @PathVariable String lastName) {
        return personService.readById(firstName, lastName);
    }

    /**
     * Creates a new person.
     *
     * @param person the person to create
     * @return the created person
     */
    @PostMapping("/person")
    public Person create(@RequestBody Person person) {
        personService.create(person);
        return person;
    }

    /**
     * Deletes a person by their first and last name.
     *
     * @param firstName the first name of the person
     * @param lastName  the last name of the person
     * @return a message indicating the person was deleted
     */
    @DeleteMapping("/person/{firstName}.{lastName}")
    public String delete(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deleteById(firstName, lastName);
        return "Person deleted " + firstName + " " + lastName;
    }

    /**
     * Updates an existing person.
     *
     * @param updatedPerson the updated person data
     * @param firstName     the first name of the person
     * @param lastName      the last name of the person
     * @return the updated person
     */
    @PutMapping("/person/{firstName}.{lastName}")
    public Person update(@RequestBody Person updatedPerson,
            @PathVariable String firstName, @PathVariable String lastName) {
        personService.update(updatedPerson, firstName, lastName);
        return updatedPerson;
    }
}
