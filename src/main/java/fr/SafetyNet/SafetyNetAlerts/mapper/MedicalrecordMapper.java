package fr.SafetyNet.SafetyNetAlerts.mapper;

import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
public interface MedicalrecordMapper {

    Medicalrecord toCreateDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromCreateDTO(Medicalrecord medicalRecordCreateDTO);

    Medicalrecord toUpdateDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromUpdateDTO(Medicalrecord medicalRecordUpdateDTO);

    Medicalrecord toResponseDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromResponseDTO(Medicalrecord medicalRecordResponseDTO);

}
