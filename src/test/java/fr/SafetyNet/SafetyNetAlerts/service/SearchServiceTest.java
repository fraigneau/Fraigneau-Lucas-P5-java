package fr.SafetyNet.SafetyNetAlerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForChildAlert;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFire;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFloodStations;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SearchServiceTest {

    @Mock
    private PersonMapper personMapper;

    @Mock
    private FirestationService firestationService;

    @Mock
    private MedicalrecordService medicalrecordService;

    @Mock
    private PersonService personService;

    private SearchService searchService;

    private Firestation firestation;
    private Person personAdult;
    private Person personChild;
    private Medicalrecord adultMedicalRecord;
    private Medicalrecord childMedicalRecord;

    @BeforeEach
    public void setUp() {
        firestation = new Firestation();
        firestation.setAddress("123 Main St");
        firestation.setStation(1);

        personAdult = new Person();
        personAdult.setFirstName("John");
        personAdult.setLastName("Doe");
        personAdult.setAddress("123 Main St");
        personAdult.setPhone("111-111-1111");
        personAdult.setCity("City");
        personAdult.setEmail("john.doe@example.com");

        personChild = new Person();
        personChild.setFirstName("Jane");
        personChild.setLastName("Doe");
        personChild.setAddress("123 Main St");
        personChild.setPhone("222-222-2222");
        personChild.setCity("City");
        personChild.setEmail("jane.doe@example.com");

        adultMedicalRecord = new Medicalrecord();
        adultMedicalRecord.setFirstName("John");
        adultMedicalRecord.setLastName("Doe");
        adultMedicalRecord.setBirthdate("01/01/1980");
        adultMedicalRecord.setMedications(List.of("med1", "med2"));
        adultMedicalRecord.setAllergies(List.of("allergy1"));

        childMedicalRecord = new Medicalrecord();
        childMedicalRecord.setFirstName("Jane");
        childMedicalRecord.setLastName("Doe");
        childMedicalRecord.setBirthdate("01/01/2010");
        childMedicalRecord.setMedications(List.of("medA"));
        childMedicalRecord.setAllergies(List.of("allergyA"));

        lenient().when(firestationService.readAll()).thenReturn(List.of(firestation));
        lenient().when(personService.readAll()).thenReturn(List.of(personAdult, personChild));
        lenient().when(medicalrecordService.readAll()).thenReturn(List.of(adultMedicalRecord, childMedicalRecord));

        RPersonForFirestationCoverage dtoAdult = new RPersonForFirestationCoverage();
        RPersonForFirestationCoverage dtoChild = new RPersonForFirestationCoverage();
        lenient().when(personMapper.toPersonForFirestationCoverageResponseDTO(personAdult)).thenReturn(dtoAdult);
        lenient().when(personMapper.toPersonForFirestationCoverageResponseDTO(personChild)).thenReturn(dtoChild);

        lenient().when(personMapper.toResponseDTO(personAdult)).thenReturn(personAdult);
        lenient().when(personMapper.toResponseDTO(personChild)).thenReturn(personChild);

        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
    }

    @Test
    public void testGetCoveredPersonsByStation_ReturnsCorrectCoverage() {
        RFirestationCoverage result = searchService.getCoveredPersonsByStation(1);
        assertEquals(1, result.getAdultCount().get(), "The number of adults should be 1");
        assertEquals(1, result.getChildCount().get(), "The number of children should be 1");
        assertEquals(2, result.getPersons().size(), "There should be 2 covered persons");
    }

    @Test
    public void testGetCoveredPersonsByStation_FirestationNotFound_ThrowsException() {
        when(firestationService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getCoveredPersonsByStation(99));
        assertEquals("No firestations found for station number: 99", exception.getMessage());
    }

    @Test
    public void testGetChildrenByAddress_NoResidents_ReturnsEmptyChildAlert() {
        when(personService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        RChildAlert result = searchService.getChildrenByAddress("Unknown Address");
        assertEquals(0, result.getChildren().size(), "The children list should be empty");
    }

    @Test
    public void testGetChildrenByAddress_WithChildren_ReturnsCorrectChildAlert() {
        RChildAlert result = searchService.getChildrenByAddress("123 Main St");
        assertEquals(1, result.getChildren().size(), "There should be 1 child");
        RPersonForChildAlert childAlert = result.getChildren().get(0);
        assertEquals("Jane", childAlert.getFirstName(), "First name should be Jane");
        assertEquals("Doe", childAlert.getLastName(), "Last name should be Doe");
    }

    @Test
    public void testGetPhonesByStation_ReturnsCorrectPhones() {
        RPhoneAlert result = searchService.getPhonesByStation(1);
        assertEquals(2, result.getPhones().size(), "There should be 2 phone numbers");
        List<String> phones = result.getPhones();
        boolean containsPhone1 = phones.contains("111-111-1111");
        boolean containsPhone2 = phones.contains("222-222-2222");
        assertEquals(true, containsPhone1, "Phone number 111-111-1111 should be present");
        assertEquals(true, containsPhone2, "Phone number 222-222-2222 should be present");
    }

    @Test
    public void testGetPhonesByStation_FirestationNotFound_ThrowsException() {
        when(firestationService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getPhonesByStation(99));
        assertEquals("No firestations found for station number: 99", exception.getMessage());
    }

    @Test
    public void testGetPersonsByAddressStation_ReturnsCorrectFireData() {
        RFire result = searchService.getPersonsByAddressStation("123 Main St");
        assertEquals(1, result.getStations().size(), "There should be 1 station associated with the address");
        assertEquals(2, result.getPersons().size(), "There should be 2 residents");
        RPersonForFire personForFire = result.getPersons().get(0);
        int age = personForFire.getAge();
        int expectedAgeAdult = LocalDate.now().getYear() - 1980;
        int expectedAgeChild = LocalDate.now().getYear() - 2010;
        boolean validAge = (age == expectedAgeAdult) || (age == expectedAgeChild);
        assertEquals(true, validAge, "The age should match the birth year");
    }

    @Test
    public void testGetPersonsByAddressStation_NoFirestation_ThrowsException() {
        when(firestationService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getPersonsByAddressStation("123 Main St"));
        assertEquals("No firestation found for address: 123 Main St", exception.getMessage());
    }

    @Test
    public void testGetPersonsByAddressStation_NoResidents_ThrowsException() {
        when(personService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getPersonsByAddressStation("123 Main St"));
        assertEquals("No residents found for the address: 123 Main St", exception.getMessage());
    }

    @Test
    public void testGetPersonsByStationsWithMedicalRecord_ReturnsCorrectFloodData() {
        RFloodStations result = searchService.getPersonsByStationsWithMedicalRecord(List.of(1));
        assertEquals(2, result.getPersons().size(), "There should be 2 persons in the flood zone");
        RPersonForFloodStations floodResident = result.getPersons().get(0);
        assertEquals("123 Main St", floodResident.getAddress(), "The address should be 123 Main St");
    }

    @Test
    public void testGetPersonsByStationsWithMedicalRecord_NoFirestationFound_ThrowsException() {
        when(firestationService.readAll()).thenReturn(List.of());
        searchService = new SearchService(personMapper, firestationService, medicalrecordService, personService);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getPersonsByStationsWithMedicalRecord(List.of(99)));
        assertEquals("Resource not found for station numbers: [99]", exception.getMessage());
    }

    @Test
    public void testGetPersonByLastNameWithMedicalRecord_ReturnsCorrectData() {
        RPersonsInfoLastName result = searchService.getPersonByLastNameWithMedicalRecord("Doe");
        assertEquals(2, result.getPersons().size(), "There should be 2 persons with the last name Doe");
    }

    @Test
    public void testGetPersonByLastNameWithMedicalRecord_NotFound_ThrowsException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getPersonByLastNameWithMedicalRecord("Unknown"));
        assertEquals("Resource not found for the lastName: Unknown", exception.getMessage());
    }

    @Test
    public void testGetEmailsByCity_ReturnsCorrectEmails() {
        RCommunityEmail result = searchService.getEmailsByCity("City");
        assertEquals(2, result.getEmails().size(), "There should be 2 emails for the city 'City'");
        boolean containsEmail = result.getEmails().contains("john.doe@example.com");
        assertEquals(true, containsEmail, "The email john.doe@example.com should be present");
    }

    @Test
    public void testGetEmailsByCity_NotFound_ThrowsException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> searchService.getEmailsByCity("UnknownCity"));
        assertEquals("Resource not found for the city: UnknownCity", exception.getMessage());
    }
}
