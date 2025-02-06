package fr.SafetyNet.SafetyNetAlerts.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.SafetyNet.SafetyNetAlerts.dto.search.RChildAlert;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RCommunityEmail;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFire;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RFloodStations;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPersonsInfoLastName;
import fr.SafetyNet.SafetyNetAlerts.dto.search.RPhoneAlert;
import fr.SafetyNet.SafetyNetAlerts.service.SearchService;

public class SearchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void testGetCoveredPersonsByStation() throws Exception {
        RFirestationCoverage coverage = new RFirestationCoverage();
        when(searchService.getCoveredPersonsByStation(anyInt())).thenReturn(coverage);

        mockMvc.perform(get("/firestationCoverage")
                .param("stationNumber", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetChildrenByAddress() throws Exception {
        RChildAlert childAlert = new RChildAlert();
        when(searchService.getChildrenByAddress(anyString())).thenReturn(childAlert);

        mockMvc.perform(get("/childAlert")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPhonesByStation() throws Exception {
        RPhoneAlert phoneAlert = new RPhoneAlert();
        when(searchService.getPhonesByStation(anyInt())).thenReturn(phoneAlert);

        mockMvc.perform(get("/phoneAlert")
                .param("stationNumber", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonsByAddressStation() throws Exception {
        RFire fire = new RFire();
        when(searchService.getPersonsByAddressStation(anyString())).thenReturn(fire);

        mockMvc.perform(get("/fire")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonsByStationsWithMedicalRecord() throws Exception {
        RFloodStations floodStations = new RFloodStations();
        when(searchService.getPersonsByStationsWithMedicalRecord(anyList())).thenReturn(floodStations);

        mockMvc.perform(get("/flood/stations")
                .param("stationNumbers", "1", "2", "3"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonByLastNameWithMedicalRecord() throws Exception {
        RPersonsInfoLastName personsInfo = new RPersonsInfoLastName();
        when(searchService.getPersonByLastNameWithMedicalRecord(anyString())).thenReturn(personsInfo);

        String lastName = "Boyd";
        mockMvc.perform(get("/personInfoLastName=" + lastName))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmailsByCity() throws Exception {
        RCommunityEmail communityEmail = new RCommunityEmail();
        when(searchService.getEmailsByCity(anyString())).thenReturn(communityEmail);

        mockMvc.perform(get("/communityEmail")
                .param("city", "Culver"))
                .andExpect(status().isOk());
    }
}
