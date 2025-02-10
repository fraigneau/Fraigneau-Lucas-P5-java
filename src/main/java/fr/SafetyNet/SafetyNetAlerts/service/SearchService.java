package fr.SafetyNet.SafetyNetAlerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForChildAlert;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFire;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFloodStations;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForPersonsInfoLastName;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RChildAlert;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RCommunityEmail;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFire;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFloodStations;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPersonsInfoLastName;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPhoneAlert;
import fr.SafetyNet.SafetyNetAlerts.exception.ResourceNotFoundException;
import fr.SafetyNet.SafetyNetAlerts.mapper.PersonMapper;
import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;

/**
 * Service for handling search-related operations.
 */
@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);
    private final PersonMapper personMapper;

    List<Person> persons;
    List<Firestation> firestations;
    List<Medicalrecord> medicalrecords;

    /**
     * Constructs a new SearchService with the specified dependencies.
     *
     * @param personMapper         the mapper to convert Person entities to DTOs
     * @param firestationService   the service to manage firestations
     * @param medicalrecordService the service to manage medical records
     * @param personService        the service to manage persons
     */
    public SearchService(PersonMapper personMapper, FirestationService firestationService,
            MedicalrecordService medicalrecordService, PersonService personService) {
        this.firestations = firestationService.readAll();
        this.medicalrecords = medicalrecordService.readAll();
        this.persons = personService.readAll();
        this.personMapper = personMapper;
    }

    /**
     * Retrieves the coverage of persons by a fire station.
     *
     * @param stationNumber the number of the fire station
     * @return the coverage details of the fire station
     */
    public RFirestationCoverage getCoveredPersonsByStation(int stationNumber) {
        AtomicInteger adultCount = new AtomicInteger();
        AtomicInteger childCount = new AtomicInteger();

        List<Firestation> firestationsWithSameNumberStation = firestations.stream()
                .filter(firestation -> firestation.getStation() == stationNumber)
                .toList();

        if (firestationsWithSameNumberStation.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("No firestations found for station number: " + stationNumber);
        }

        List<RPersonForFirestationCoverage> coveredPersons = persons.stream()
                .filter(person -> firestationsWithSameNumberStation.stream()
                        .anyMatch(firestation -> firestation.getAddress().equals(person.getAddress())))
                .map((Person person) -> {
                    if (getAge(person) >= 18) {
                        adultCount.incrementAndGet();
                    } else {
                        childCount.incrementAndGet();
                    }
                    return personMapper.toPersonForFirestationCoverageResponseDTO(person);
                })
                .toList();

        return new RFirestationCoverage(adultCount, childCount, coveredPersons);
    }

    /**
     * Retrieves children by address.
     *
     * @param address the address to search for children
     * @return the details of children at the specified address
     */
    public RChildAlert getChildrenByAddress(String address) {
        List<Person> residents = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        if (residents.isEmpty()) {
            logger.warn("Resource not found");
            return new RChildAlert(List.of());
        }

        List<RPersonForChildAlert> children = residents.stream()
                .filter(resident -> getAge(resident) <= 18)
                .map(child -> {
                    List<Person> relatives = residents.stream()
                            .filter(resident -> !resident.equals(child))
                            .map(personMapper::toResponseDTO)
                            .toList();

                    return new RPersonForChildAlert(
                            child.getFirstName(),
                            child.getLastName(),
                            getAge(child),
                            relatives);
                })
                .toList();

        return new RChildAlert(children);
    }

    /**
     * Retrieves phone numbers by fire station number.
     *
     * @param stationNumber the number of the fire station
     * @return the phone numbers associated with the fire station
     */
    public RPhoneAlert getPhonesByStation(int stationNumber) {
        List<Firestation> firestationsWithSameNumberStation = firestations.stream()
                .filter(firestation -> firestation.getStation() == stationNumber)
                .toList();

        if (firestationsWithSameNumberStation.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("No firestations found for station number: " + stationNumber);
        }

        List<String> phones = persons.stream()
                .filter(person -> firestationsWithSameNumberStation.stream()
                        .anyMatch(firestation -> firestation.getAddress().equals(person.getAddress())))
                .map(Person::getPhone)
                .toList();

        return new RPhoneAlert(phones);
    }

    public RFire getPersonsByAddressStation(String address) {

        List<Integer> stations = firestations.stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .map(Firestation::getStation)
                .toList();

        if (stations.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("No firestation found for address: " + address);
        }

        List<Person> residents = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        if (residents.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("No residents found for the address: " + address);
        }

        List<RPersonForFire> persons = residents.stream()
                .map(person -> new RPersonForFire(
                        person.getLastName(),
                        person.getPhone(),
                        getAge(person),
                        getMedications(person),
                        getAllergies(person)))
                .toList();

        return new RFire(
                stations,
                persons);
    }

    public RFloodStations getPersonsByStationsWithMedicalRecord(List<Integer> stationNumbers) {

        List<String> firestationsByAddress = new ArrayList<>();

        for (Integer stationNumber : stationNumbers) {
            firestations.stream()
                    .filter(firestation -> firestation.getStation() == stationNumber)
                    .map(Firestation::getAddress)
                    .forEach(firestationsByAddress::add);
        }

        if (firestationsByAddress.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("Resource not found for station numbers: " + stationNumbers);
        }

        List<Person> residents = persons.stream()
                .filter(person -> firestationsByAddress.contains(person.getAddress()))
                .toList();

        if (residents.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("No residents found for the given stations.");
        }

        List<RPersonForFloodStations> persons = residents.stream()
                .map(person -> new RPersonForFloodStations(
                        person.getAddress(),
                        person.getLastName(),
                        person.getPhone(),
                        getAge(person),
                        getMedications(person),
                        getAllergies(person)))
                .toList();

        return new RFloodStations(persons);
    }

    public RPersonsInfoLastName getPersonByLastNameWithMedicalRecord(String lastName) {

        List<RPersonForPersonsInfoLastName> personsTargeted = persons.stream()
                .filter(person -> person.getLastName().equals(lastName))
                .map(person -> new RPersonForPersonsInfoLastName(
                        person.getLastName(),
                        person.getAddress(),
                        getAge(person),
                        person.getEmail(),
                        getMedications(person),
                        getAllergies(person)))
                .toList();

        if (personsTargeted.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("Resource not found for the lastName: " + lastName);
        }

        return new RPersonsInfoLastName(personsTargeted);
    }

    public RCommunityEmail getEmailsByCity(String city) {

        List<String> emails = persons.stream()
                .filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .toList();

        if (emails.isEmpty()) {
            logger.warn("Resource not found");
            throw new ResourceNotFoundException("Resource not found for the city: " + city);
        }

        return new RCommunityEmail(emails);
    }

    private Medicalrecord getMedicalRecord(Person person) {
        return medicalrecords.stream()
                .filter(record -> record.getFirstName().equals(person.getFirstName()) &&
                        record.getLastName().equals(person.getLastName()))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warn("Resource not found");
                    return new ResourceNotFoundException("Medical record not found");
                });
    }

    public List<String> getMedications(Person person) {
        Medicalrecord record = getMedicalRecord(person);
        return record.getMedications();
    }

    public List<String> getAllergies(Person person) {
        Medicalrecord record = getMedicalRecord(person);
        return record.getAllergies();
    }

    public int getAge(Person person) {
        Medicalrecord record = getMedicalRecord(person);
        LocalDate dateOfBirth = LocalDate.parse(record.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
