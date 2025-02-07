package fr.SafetyNet.SafetyNetAlerts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.SafetyNet.SafetyNetAlerts.model.Person;

class JsonDataRepositoryTest {

    private static final Path JSON = Paths.get("Data/data.json");
    private static final Path PROD_JSON = Paths.get("src/main/resources/data.json");

    private JsonDataRepository repository;

    @BeforeEach
    void copyTestFileIntoProduction() throws IOException {
        if (Files.exists(JSON)) {
            Files.copy(JSON, PROD_JSON, StandardCopyOption.REPLACE_EXISTING);
        }

        repository = new JsonDataRepository();
        repository.loadJsonFile();
    }

    @Test
    void testLoadJsonFile() throws IOException {
        List<Person> persons = repository.getList(Person.class);

        assertNotNull(persons, "La liste de Person ne doit pas être nulle");
        assertFalse(persons.isEmpty(), "La liste de Person ne doit pas être vide (selon data-test.json)");
    }

    @Test
    void testGetListPerson() throws IOException {
        List<Person> persons = repository.getList(Person.class);

        assertEquals(23, persons.size(), "La taille doit correspondre aux données de test");
        assertEquals("John", persons.get(0).getFirstName(), "Premier prénom attendu");
        assertEquals("Boyd", persons.get(0).getLastName(), "Premier nom attendu");
    }

    @Test
    void testGetList_WhenJsonFieldIsNotArray_ShouldThrowException() {
        class UnknownClass {
        }
        assertThrows(IllegalArgumentException.class, () -> repository.getList(UnknownClass.class));
    }

    @Test
    void testSetList_ShouldUpdateJsonFile() throws IOException {
        List<Person> initialPersons = repository.getList(Person.class);
        int initialSize = initialPersons.size();

        Person newPerson = new Person();
        newPerson.setFirstName("JaketheDog");
        newPerson.setLastName("Doe");
        newPerson.setAddress("123 Fake St");
        newPerson.setCity("Springfield");
        newPerson.setZip(12345);
        newPerson.setPhone("123-456-7890");
        newPerson.setEmail("JaketheDog@test.com");

        initialPersons.add(newPerson);

        repository.setList(Person.class, initialPersons);

        repository.loadJsonFile();
        List<Person> updatedPersons = repository.getList(Person.class);

        assertEquals(initialSize + 1, updatedPersons.size(),
                "La taille devrait avoir augmenté de 1");
        assertTrue(
                updatedPersons.stream().anyMatch(p -> "JaketheDog".equals(p.getFirstName())),
                "On doit trouver 'Jake' dans les personnes écrites");
    }
}
