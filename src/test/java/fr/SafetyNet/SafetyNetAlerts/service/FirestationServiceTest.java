package fr.SafetyNet.SafetyNetAlerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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
import org.mockito.junit.jupiter.MockitoExtension;

import fr.SafetyNet.SafetyNetAlerts.exception.ConflictException;
import fr.SafetyNet.SafetyNetAlerts.exception.ResourceNotFoundException;
import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

    @Mock
    private JsonDataRepository jsonDataRepo;

    @InjectMocks
    private FirestationService firestationService;

    private List<Firestation> mockFireStations;

    @BeforeEach
    void setUp() throws IOException {
        mockFireStations = new ArrayList<>();
        Firestation f1 = new Firestation();
        f1.setAddress("123 Main St");
        f1.setStation(1);

        Firestation f2 = new Firestation();
        f2.setAddress("456 Oak Ave");
        f2.setStation(2);

        mockFireStations.add(f1);
        mockFireStations.add(f2);

        when(jsonDataRepo.getList(Firestation.class)).thenReturn(mockFireStations);

        firestationService = new FirestationService(jsonDataRepo);
    }

    @Test
    void testReadAll() throws IOException {
        List<Firestation> fireStations = firestationService.readAll();
        assertEquals(2, fireStations.size());
        verify(jsonDataRepo, times(2)).getList(Firestation.class);
    }

    @Test
    void testReadById_Found() {
        Firestation found = firestationService.readById("123 Main St");
        assertNotNull(found);
        assertEquals("123 Main St", found.getAddress());
    }

    @Test
    void testReadById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            firestationService.readById("999 Unknown Rd");
        });
    }

    @Test
    void testCreate_Success() throws IOException {
        Firestation newFireStation = new Firestation();
        newFireStation.setAddress("789 Pine Ln");
        newFireStation.setStation(3);

        Firestation created = firestationService.create(newFireStation);

        assertNotNull(created);
        assertEquals("789 Pine Ln", created.getAddress());
        assertEquals(3, mockFireStations.size());

        verify(jsonDataRepo, times(1)).setList(Firestation.class, mockFireStations);
    }

    @Test
    void testCreate_Conflict() throws IOException {
        Firestation duplicate = new Firestation();
        duplicate.setAddress("123 Main St");
        duplicate.setStation(4);

        assertThrows(ConflictException.class, () -> {
            firestationService.create(duplicate);
        });

        assertEquals(2, mockFireStations.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testDeleteById_Success() throws IOException {
        firestationService.deleteById("456 Oak Ave");
        assertEquals(1, mockFireStations.size());
        verify(jsonDataRepo, times(1)).setList(Firestation.class, mockFireStations);
    }

    @Test
    void testDeleteById_NotFound() throws IOException {
        assertThrows(ResourceNotFoundException.class, () -> {
            firestationService.deleteById("999 Unknown Rd");
        });

        assertEquals(2, mockFireStations.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testUpdate_Success() {
        Firestation updated = new Firestation();
        updated.setAddress("123 Main St");
        updated.setStation(5);

        Firestation result = firestationService.update(updated, "123 Main St");

        assertNotNull(result);
        assertEquals(5, result.getStation());
        assertEquals(5, mockFireStations.get(0).getStation());
    }

    @Test
    void testUpdate_NotFound() {
        Firestation updated = new Firestation();
        updated.setAddress("999 Unknown Rd");
        updated.setStation(6);

        assertThrows(ResourceNotFoundException.class, () -> {
            firestationService.update(updated, "999 Unknown Rd");
        });
    }
}