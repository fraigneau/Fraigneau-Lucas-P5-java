package fr.SafetyNet.SafetyNetAlerts.mapper;

import org.mapstruct.Mapper;

import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
@Mapper(componentModel = "spring")
public interface MedicalrecordMapper {

    Medicalrecord toCreateDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromCreateDTO(Medicalrecord medicalRecordCreateDTO);

    Medicalrecord toUpdateDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromUpdateDTO(Medicalrecord medicalRecordUpdateDTO);

    Medicalrecord toResponseDTO(Medicalrecord medicalRecord);

    Medicalrecord toEntityFromResponseDTO(Medicalrecord medicalRecordResponseDTO);

}
