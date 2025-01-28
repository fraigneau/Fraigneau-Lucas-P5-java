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

@RestController
public class RestPerson {

    private final PersonService personService;

    public RestPerson(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> readAll() {
        return personService.readAll();
    }

    @GetMapping("person/{firstName}.{lastName}")
    public Person readById(@PathVariable String firstName, @PathVariable String lastName) {
        return personService.readById(firstName, lastName);
    }

    @PostMapping("/person")
    public String create(@RequestBody Person person) {
        personService.Create(person);
        return "Person created " + person.getFirstName() + " " +
                person.getLastName();

    }

    @DeleteMapping("/person/{firstName}.{lastName}")
    public String delete(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deleteById(firstName, lastName);
        return "Person deleted " + firstName + " " +
                lastName;
    }

    @PutMapping("/person/{firstName}.{lastName}")
    public String update(@RequestBody Person updatedPerson,
            @PathVariable String firstName, @PathVariable String lastName) {
        personService.update(updatedPerson, firstName, lastName);
        return "Person created " + updatedPerson.getFirstName() + " " +
                updatedPerson.getLastName();
    }
}
