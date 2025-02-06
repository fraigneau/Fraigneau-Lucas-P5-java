package fr.SafetyNet.SafetyNetAlerts.mapper;

import org.mapstruct.Mapper;

import fr.SafetyNet.SafetyNetAlerts.model.Firestation;

@Mapper(componentModel = "spring")
public interface FirestationMapper {

    Firestation toCreateDTO(Firestation firestation);

    Firestation toEntityFromCreateDTO(Firestation firestationCreateDTO);

    Firestation toUpdateDTO(Firestation firestation);

    Firestation toEntityFromUpdateDTO(Firestation firestationUpdateDTO);

    Firestation toResponseDTO(Firestation firestation);

    Firestation toEntityFromResponseDTO(Firestation firestationResponseDTO);

}
