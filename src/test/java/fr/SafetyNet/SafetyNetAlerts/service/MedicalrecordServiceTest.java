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
import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.repository.JsonDataRepository;

@ExtendWith(MockitoExtension.class)
class MedicalrecordServiceTest {

    @Mock
    private JsonDataRepository jsonDataRepo;

    @InjectMocks
    private MedicalrecordService medicalrecordService;

    private List<Medicalrecord> mockMedicalRecords;

    @BeforeEach
    void setUp() throws IOException {
        mockMedicalRecords = new ArrayList<>();
        Medicalrecord m1 = new Medicalrecord();
        m1.setFirstName("John");
        m1.setLastName("Doe");
        m1.setBirthdate("01/01/1990");
        m1.setMedications(List.of("med1", "med2"));
        m1.setAllergies(List.of("allergy1"));

        Medicalrecord m2 = new Medicalrecord();
        m2.setFirstName("Jane");
        m2.setLastName("Doe");
        m2.setBirthdate("02/02/1992");
        m2.setMedications(List.of("med3"));
        m2.setAllergies(List.of("allergy2", "allergy3"));

        mockMedicalRecords.add(m1);
        mockMedicalRecords.add(m2);

        when(jsonDataRepo.getList(Medicalrecord.class)).thenReturn(mockMedicalRecords);

        medicalrecordService = new MedicalrecordService(jsonDataRepo);
    }

    @Test
    void testReadAll() throws IOException {
        List<Medicalrecord> records = medicalrecordService.readAll();
        assertEquals(2, records.size());
        verify(jsonDataRepo, Mockito.times(2)).getList(Medicalrecord.class);
    }

    @Test
    void testReadById_Found() {
        Medicalrecord foundRecord = medicalrecordService.readById("John", "Doe");
        assertNotNull(foundRecord);
        assertEquals("John", foundRecord.getFirstName());
        assertEquals("Doe", foundRecord.getLastName());
    }

    @Test
    void testReadById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            medicalrecordService.readById("Unknown", "Person");
        });
    }

    @Test
    void testCreate_Success() throws IOException {
        Medicalrecord newRecord = new Medicalrecord();
        newRecord.setFirstName("Bob");
        newRecord.setLastName("Martin");
        newRecord.setBirthdate("03/03/1993");
        newRecord.setMedications(List.of("med4"));
        newRecord.setAllergies(List.of("allergy4"));

        Medicalrecord created = medicalrecordService.create(newRecord);

        assertNotNull(created);
        assertEquals("Bob", created.getFirstName());
        assertEquals(3, mockMedicalRecords.size());
        verify(jsonDataRepo, Mockito.times(1)).setList(Medicalrecord.class, mockMedicalRecords);
    }

    @Test
    void testCreate_Conflict() throws IOException {
        Medicalrecord duplicate = new Medicalrecord();
        duplicate.setFirstName("John");
        duplicate.setLastName("Doe");
        duplicate.setBirthdate("04/04/1994");
        duplicate.setMedications(List.of("med5"));
        duplicate.setAllergies(List.of("allergy5"));

        assertThrows(ConflictException.class, () -> {
            medicalrecordService.create(duplicate);
        });

        assertEquals(2, mockMedicalRecords.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testDeleteById_Success() throws IOException {
        medicalrecordService.deleteById("Jane", "Doe");
        assertEquals(1, mockMedicalRecords.size());
        verify(jsonDataRepo, Mockito.times(1)).setList(Medicalrecord.class, mockMedicalRecords);
    }

    @Test
    void testDeleteById_NotFound() throws IOException {
        assertThrows(ResourceNotFoundException.class, () -> {
            medicalrecordService.deleteById("X", "Y");
        });
        assertEquals(2, mockMedicalRecords.size());
        verify(jsonDataRepo, never()).setList(any(), any());
    }

    @Test
    void testUpdate_Success() {
        Medicalrecord updated = new Medicalrecord();
        updated.setFirstName("John");
        updated.setLastName("Doe");
        updated.setBirthdate("05/05/1995");
        updated.setMedications(List.of("med6"));
        updated.setAllergies(List.of("allergy6"));

        Medicalrecord result = medicalrecordService.update(updated, "John", "Doe");

        assertNotNull(result);
        assertEquals("med6", result.getMedications().get(0));
        assertEquals("allergy6", result.getAllergies().get(0));
    }

    @Test
    void testUpdate_NotFound() {
        Medicalrecord updated = new Medicalrecord();
        updated.setFirstName("Ghost");
        updated.setLastName("Person");

        assertThrows(ResourceNotFoundException.class, () -> {
            medicalrecordService.update(updated, "Ghost", "Person");
        });
    }
}