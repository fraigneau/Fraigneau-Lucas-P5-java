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

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.service.FirestationService;

public class RestFirestationTest {

    private MockMvc mockMvc;

    private static final String endPoint = "/firestation";
    private static final String Id = "/testAddress";
    private static final String address = "testAddress";

    @Mock
    private FirestationService firestationService;

    @InjectMocks
    private RestFirestation restFirestation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restFirestation).build();
    }

    @Test
    void testCreate() throws Exception {
        FireStation fireStation = new FireStation();
        when(firestationService.create(any(FireStation.class))).thenReturn(fireStation);

        String postBody = "{\"address\":\"testAddress\", \"station\":\"1\"}";

        mockMvc.perform(post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody))
                .andExpect(status().isOk());
    }

    @Test
    void testReadById() throws Exception {
        when(firestationService.readById(address)).thenReturn(new FireStation());

        mockMvc.perform(get(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        FireStation updatedFireStation = new FireStation();
        when(firestationService.update(any(FireStation.class), eq(address))).thenReturn(updatedFireStation);

        String updateBody = "{\"address\":\"testAddress2\", \"station\":\"2\"}";

        mockMvc.perform(put(endPoint + Id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(firestationService).deleteById(address);

        mockMvc.perform(delete(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testReadAll() throws Exception {
        when(firestationService.readAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(endPoint))
                .andExpect(status().isOk());
    }
}