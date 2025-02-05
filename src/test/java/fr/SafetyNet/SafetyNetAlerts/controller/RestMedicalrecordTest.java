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

import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.service.MedicalrecordService;

public class RestMedicalrecordTest {

    private MockMvc mockMvc;

    private static final String endPoint = "/medicalrecord";
    private static final String Id = "/test.test";
    private static final String firstName = "test";
    private static final String lastName = "test";

    @Mock
    private MedicalrecordService medicalrecordService;

    @InjectMocks
    private RestMedicalrecord restMedicalrecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restMedicalrecord).build();
    }

    @Test
    void testCreate() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        when(medicalrecordService.create(any(MedicalRecord.class))).thenReturn(medicalRecord);

        String postBody = "{\"firstName\":\"test\", \"lastName\":\"test\", \"birthdate\":\"01/01/2000\", \"medications\":[\"med1:500mg\"], \"allergies\":[\"peanut\"]}";

        mockMvc.perform(post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postBody))
                .andExpect(status().isOk());
    }

    @Test
    void testReadById() throws Exception {
        when(medicalrecordService.readById(firstName, lastName)).thenReturn(new MedicalRecord());

        mockMvc.perform(get(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        MedicalRecord updatedRecord = new MedicalRecord();
        when(medicalrecordService.update(any(MedicalRecord.class), eq(firstName), eq(lastName)))
                .thenReturn(updatedRecord);

        String updateBody = "{\"firstName\":\"test2\", \"lastName\":\"test2\", \"birthdate\":\"02/02/2000\", \"medications\":[\"med2:250mg\"], \"allergies\":[\"gluten\"]}";

        mockMvc.perform(put(endPoint + Id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateBody))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(medicalrecordService).deleteById(firstName, lastName);

        mockMvc.perform(delete(endPoint + Id))
                .andExpect(status().isOk());
    }

    @Test
    void testReadAll() throws Exception {
        when(medicalrecordService.readAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(endPoint))
                .andExpect(status().isOk());
    }
}
