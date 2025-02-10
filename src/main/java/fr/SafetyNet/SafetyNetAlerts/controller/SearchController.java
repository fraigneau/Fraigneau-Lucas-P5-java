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

/**
 * Controller for handling search-related requests.
 */
@Controller
@ResponseBody
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * Retrieves the coverage of persons by a fire station.
     *
     * @param stationNumber the number of the fire station
     * @return the coverage details of the fire station
     */
    @GetMapping("/firestationCoverage")
    public RFirestationCoverage getCoveredPersonsByStation(@RequestParam int stationNumber) {
        return searchService.getCoveredPersonsByStation(stationNumber);
    }

    /**
     * Retrieves children by address.
     *
     * @param address the address to search for children
     * @return the details of children at the specified address
     */
    @GetMapping("/childAlert")
    public RChildAlert getChildrenByAddress(@RequestParam String address) {
        return searchService.getChildrenByAddress(address);
    }

    /**
     * Retrieves phone numbers by fire station number.
     *
     * @param stationNumber the number of the fire station
     * @return the phone numbers associated with the fire station
     */
    @GetMapping("/phoneAlert")
    public RPhoneAlert getPhonesByStation(@RequestParam int stationNumber) {
        return searchService.getPhonesByStation(stationNumber);
    }

    /**
     * Retrieves persons by address and station.
     *
     * @param address the address to search for persons
     * @return the details of persons at the specified address
     */
    @GetMapping("/fire")
    public RFire getPersonsByAddressStation(@RequestParam String address) {
        return searchService.getPersonsByAddressStation(address);
    }

    /**
     * Retrieves persons by stations with medical records.
     *
     * @param stationNumbers the list of station numbers
     * @return the details of persons at the specified stations with medical records
     */
    @GetMapping("/flood/stations")
    public RFloodStations getPersonsByStationsWithMedicalRecord(@RequestParam List<Integer> stationNumbers) {
        return searchService.getPersonsByStationsWithMedicalRecord(stationNumbers);
    }

    /**
     * Retrieves persons by last name with medical records.
     *
     * @param lastName the last name of the person
     * @return the details of persons with the specified last name and their medical
     *         records
     */
    @GetMapping("/personInfoLastName={lastName}")
    public RPersonsInfoLastName getPersonByLastNameWithMedicalRecord(@PathVariable String lastName) {
        return searchService.getPersonByLastNameWithMedicalRecord(lastName);
    }

    /**
     * Retrieves emails by city.
     *
     * @param city the city to search for emails
     * @return the emails of persons in the specified city
     */
    @GetMapping("/communityEmail")
    public RCommunityEmail getEmailsByCity(@RequestParam String city) {
        return searchService.getEmailsByCity(city);
    }

}
