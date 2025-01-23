package fr.SafetyNet.SafetyNetAlerts.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.service.DataService;

@RestController
public class RestPerson {

    private final DataService dataService;

    public RestPerson(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return dataService.getPersons();
    }

}
