package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
    public Person create(@RequestBody Person person) {
        personService.Create(person);
        return person;
    }

    @DeleteMapping("/person/{firstName}.{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        personService.deleteById(firstName, lastName);
    }

    @PutMapping("/person/{firstName}.{lastName}")
    public Person update(@RequestBody Person updatedPerson,
            @PathVariable String firstName, @PathVariable String lastName) {
        personService.update(updatedPerson, firstName, lastName);
        return updatedPerson;
    }
}
