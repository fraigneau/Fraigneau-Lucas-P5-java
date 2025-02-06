package fr.SafetyNet.SafetyNetAlerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.SafetyNet.SafetyNetAlerts.exception.ConflictException;
import fr.SafetyNet.SafetyNetAlerts.exception.ResourceNotFoundException;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private JsonDataRepository jsonDataRepo;

    @InjectMocks
    private PersonService personService;

    private List<Person> mockPersons;

    @BeforeEach
    void setUp() throws IOException {
        mockPersons = new ArrayList<>();
        Person p1 = new Person();
        p1.setFirstName("John");
        p1.setLastName("Doe");
        p1.setAddress("Address1");
        p1.setCity("City1");
        p1.setZip(1);
        p1.setPhone("Phone1");
        p1.setEmail("Email1");

        Person p2 = new Person();
        p2.setFirstName("Jane");
        p2.setLastName("Doe");
        p2.setAddress("Address2");
        p2.setCity("City2");
        p2.setZip(2);
        p2.setPhone("Phone2");
        p2.setEmail("Email2");

        mockPersons.add(p1);
        mockPersons.add(p2);

        when(jsonDataRepo.getList(Person.class)).thenReturn(mockPersons);

        personService = new PersonService(jsonDataRepo);
    }

    @Test
    void testReadAll() throws IOException {
        List<Person> persons = personService.readAll();

        assertEquals(2, persons.size());
        verify(jsonDataRepo, Mockito.times(2)).getList(Person.class);
    }

    @Test
    void testReadById_Found() {
        Person foundPerson = personService.readById("John", "Doe");

        assertNotNull(foundPerson);
        assertEquals("John", foundPerson.getFirstName());
        assertEquals("Doe", foundPerson.getLastName());
    }

    @Test
    void testReadById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            personService.readById("Unknown", "Person");
        });
    }

    @Test
    void testCreate_Success() throws IOException {
        Person newPerson = new Person();
        newPerson.setFirstName("Bob");
        newPerson.setLastName("Martin");
        newPerson.setAddress("Address3");
        newPerson.setCity("City3");
        newPerson.setZip(3);
        newPerson.setPhone("Phone3");
        newPerson.setEmail("Email3");

        Person created = personService.create(newPerson);

        assertNotNull(created);
        assertEquals("Bob", created.getFirstName());
        assertEquals(3, mockPersons.size()); // La liste doit avoir 3 éléments désormais

        verify(jsonDataRepo, Mockito.times(1)).setList(Person.class, mockPersons);
    }

    @Test
    void testCreate_Conflict() throws IOException {
        Person duplicate = new Person();
        duplicate.setFirstName("John");
        duplicate.setLastName("Doe");
        duplicate.setAddress("NewAddress");
        duplicate.setCity("NewCity");
        duplicate.setZip(123);
        duplicate.setPhone("NewPhone");
        duplicate.setEmail("NewEmail");

        assertThrows(ConflictException.class, () -> {
            personService.create(duplicate);
        });

        assertEquals(2, mockPersons.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testDeleteById_Success() throws IOException {
        personService.deleteById("Jane", "Doe");

        assertEquals(1, mockPersons.size());

        verify(jsonDataRepo, Mockito.times(1)).setList(Person.class, mockPersons);
    }

    @Test
    void testDeleteById_NotFound() throws IOException {
        assertThrows(ResourceNotFoundException.class, () -> {
            personService.deleteById("X", "Y");
        });

        assertEquals(2, mockPersons.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testUpdate_Success() {
        Person updated = new Person();
        updated.setFirstName("John");
        updated.setLastName("Doe");
        updated.setAddress("UpdatedAddress");
        updated.setCity("UpdatedCity");
        updated.setZip(123);
        updated.setPhone("UpdatedPhone");
        updated.setEmail("UpdatedEmail");

        Person result = personService.update(updated, "John", "Doe");

        assertNotNull(result);
        assertEquals("UpdatedAddress", result.getAddress());

        Person p1 = mockPersons.get(0);
        assertEquals("UpdatedAddress", p1.getAddress());
    }

    @Test
    void testUpdate_NotFound() {
        Person updated = new Person();
        updated.setFirstName("Ghost");
        updated.setLastName("Person");

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.update(updated, "Ghost", "Person");
        });
    }

}
