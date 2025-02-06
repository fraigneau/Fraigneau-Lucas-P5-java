package fr.SafetyNet.SafetyNetAlerts.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
@Component
public class MedicalrecordMapperImpl implements MedicalrecordMapper {

    @Override
    public Medicalrecord toCreateDTO(Medicalrecord medicalRecord) {
        if (medicalRecord == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecord.getFirstName());
        medicalrecord.setLastName(medicalRecord.getLastName());
        medicalrecord.setBirthdate(medicalRecord.getBirthdate());
        List<String> list = medicalRecord.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecord.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }

    @Override
    public Medicalrecord toEntityFromCreateDTO(Medicalrecord medicalRecordCreateDTO) {
        if (medicalRecordCreateDTO == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecordCreateDTO.getFirstName());
        medicalrecord.setLastName(medicalRecordCreateDTO.getLastName());
        medicalrecord.setBirthdate(medicalRecordCreateDTO.getBirthdate());
        List<String> list = medicalRecordCreateDTO.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecordCreateDTO.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }

    @Override
    public Medicalrecord toUpdateDTO(Medicalrecord medicalRecord) {
        if (medicalRecord == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecord.getFirstName());
        medicalrecord.setLastName(medicalRecord.getLastName());
        medicalrecord.setBirthdate(medicalRecord.getBirthdate());
        List<String> list = medicalRecord.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecord.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }

    @Override
    public Medicalrecord toEntityFromUpdateDTO(Medicalrecord medicalRecordUpdateDTO) {
        if (medicalRecordUpdateDTO == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecordUpdateDTO.getFirstName());
        medicalrecord.setLastName(medicalRecordUpdateDTO.getLastName());
        medicalrecord.setBirthdate(medicalRecordUpdateDTO.getBirthdate());
        List<String> list = medicalRecordUpdateDTO.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecordUpdateDTO.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }

    @Override
    public Medicalrecord toResponseDTO(Medicalrecord medicalRecord) {
        if (medicalRecord == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecord.getFirstName());
        medicalrecord.setLastName(medicalRecord.getLastName());
        medicalrecord.setBirthdate(medicalRecord.getBirthdate());
        List<String> list = medicalRecord.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecord.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }

    @Override
    public Medicalrecord toEntityFromResponseDTO(Medicalrecord medicalRecordResponseDTO) {
        if (medicalRecordResponseDTO == null) {
            return null;
        }

        Medicalrecord medicalrecord = new Medicalrecord();

        medicalrecord.setFirstName(medicalRecordResponseDTO.getFirstName());
        medicalrecord.setLastName(medicalRecordResponseDTO.getLastName());
        medicalrecord.setBirthdate(medicalRecordResponseDTO.getBirthdate());
        List<String> list = medicalRecordResponseDTO.getMedications();
        if (list != null) {
            medicalrecord.setMedications(new ArrayList<String>(list));
        }
        List<String> list1 = medicalRecordResponseDTO.getAllergies();
        if (list1 != null) {
            medicalrecord.setAllergies(new ArrayList<String>(list1));
        }

        return medicalrecord;
    }
}
