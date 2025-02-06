package fr.SafetyNet.SafetyNetAlerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.service.PersonService;

public class RestPersonTest {

    private MockMvc mockMvc;

    // Endpoint et identifiants pour les tests (ici, nous utilisons firstName et
    // lastName)
    private static final String endPoint = "/person";
    private static final String Id = "/test.test";
    private static final String firstName = "test";
    private static final String lastName = "test";

    @Mock
    private PersonService personService;

    @InjectMocks
    private RestPerson restPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restPerson).build();
    }

    @Test
    void testCreate() throws Exception {
        Person person = new Person();
        when(personService.create(any(Person.class))).thenReturn(person);

        // Notez que le champ zip est désormais un nombre (long) et non une chaîne de
        // caractères.
        String postBody = "{\"firstName\":\"test\", \"lastName\":\"test\", \"address\":\"test\", \"city\":\"test\", \"zip\":12345, \"phone\":\"test\", \"email\":\"test@email.com\"}";

        mockMvc.perform(post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody))
                .andExpect(status().isOk());
    }

    @Test
    void testReadById() throws Exception {
        when(personService.readById(firstName, lastName)).thenReturn(new Person());

        mockMvc.perform(get(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Person updatedPerson = new Person();
        when(personService.update(any(Person.class), eq(firstName), eq(lastName))).thenReturn(updatedPerson);

        // Ici aussi, le champ zip doit être un nombre
        String updateBody = "{\"firstName\":\"test2\", \"lastName\":\"test2\", \"address\":\"test 2\", \"city\":\"test 2\", \"zip\":67890, \"phone\":\"test 2\", \"email\":\"test2@email.com\"}";

        mockMvc.perform(put(endPoint + Id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(personService).deleteById(firstName, lastName);

        mockMvc.perform(delete(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testReadAll() throws Exception {
        when(personService.readAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(endPoint))
                .andExpect(status().isOk());
    }
}
