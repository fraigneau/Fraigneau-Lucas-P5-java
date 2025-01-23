package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RestPerson {

    private final PersonService personService;

    public RestPerson(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> getPersonsList() {
        return personService.getAll();
    }

    @GetMapping("person/{firstName}.{lastName}")
    public Person getOnePerson(@PathVariable String firstName, @PathVariable String lastName) {
        return personService.findById(firstName, lastName);
    }

    @PostMapping("/person")
    public Person postPerson(@RequestBody Person person) {
        personService.Create(person);
        return person;
    }

}
