package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.SafetyNet.SafetyNetAlerts.dto.search.RChildAlert;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RCommunityEmail;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFire;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFloodStations;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPersonsInfoLastName;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPhoneAlert;
import fr.SafetyNet.SafetyNetAlerts.service.SearchService;

@Controller
@ResponseBody
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/firestationCoverage")
    public RFirestationCoverage getCoveredPersonsByStation(@RequestParam int stationNumber) {
        return searchService.getCoveredPersonsByStation(stationNumber);
    }

    @GetMapping("/childAlert")
    public RChildAlert getChildrenByAddress(@RequestParam String address) {
        return searchService.getChildrenByAddress(address);
    }

    @GetMapping("/phoneAlert")
    public RPhoneAlert getPhonesByStation(@RequestParam int stationNumber) {
        return searchService.getPhonesByStation(stationNumber);
    }

    @GetMapping("/fire")
    public RFire getPersonsByAddressStation(@RequestParam String address) {
        return searchService.getPersonsByAddressStation(address);
    }

    @GetMapping("/flood/stations")
    public RFloodStations getPersonsByStationsWithMedicalRecord(@RequestParam List<Integer> stationNumbers) {
        return searchService.getPersonsByStationsWithMedicalRecord(stationNumbers);
    }

    @GetMapping("/personInfoLastName={lastName}")
    public RPersonsInfoLastName getPersonByLastNameWithMedicalRecord(@PathVariable String lastName) {
        return searchService.getPersonByLastNameWithMedicalRecord(lastName);
    }

    @GetMapping("/communityEmail")
    public RCommunityEmail getEmailsByCity(@RequestParam String city) {
        return searchService.getEmailsByCity(city);
    }

}
