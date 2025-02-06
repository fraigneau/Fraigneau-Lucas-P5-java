package fr.SafetyNet.SafetyNetAlerts.mapper;

import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
public interface FirestationMapper {

    Firestation toCreateDTO(Firestation firestation);

    Firestation toEntityFromCreateDTO(Firestation firestationCreateDTO);

    Firestation toUpdateDTO(Firestation firestation);

    Firestation toEntityFromUpdateDTO(Firestation firestationUpdateDTO);

    Firestation toResponseDTO(Firestation firestation);

    Firestation toEntityFromResponseDTO(Firestation firestationResponseDTO);

}
