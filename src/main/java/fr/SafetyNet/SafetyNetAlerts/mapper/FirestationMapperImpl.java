package fr.SafetyNet.SafetyNetAlerts.mapper;

import org.springframework.stereotype.Component;

import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
@Component
public class FirestationMapperImpl implements FirestationMapper {

    @Override
    public Firestation toCreateDTO(Firestation firestation) {
        if (firestation == null) {
            return null;
        }

        Firestation firestation1 = new Firestation();

        firestation1.setAddress(firestation.getAddress());
        firestation1.setStation(firestation.getStation());

        return firestation1;
    }

    @Override
    public Firestation toEntityFromCreateDTO(Firestation firestationCreateDTO) {
        if (firestationCreateDTO == null) {
            return null;
        }

        Firestation firestation = new Firestation();

        firestation.setAddress(firestationCreateDTO.getAddress());
        firestation.setStation(firestationCreateDTO.getStation());

        return firestation;
    }

    @Override
    public Firestation toUpdateDTO(Firestation firestation) {
        if (firestation == null) {
            return null;
        }

        Firestation firestation1 = new Firestation();

        firestation1.setAddress(firestation.getAddress());
        firestation1.setStation(firestation.getStation());

        return firestation1;
    }

    @Override
    public Firestation toEntityFromUpdateDTO(Firestation firestationUpdateDTO) {
        if (firestationUpdateDTO == null) {
            return null;
        }

        Firestation firestation = new Firestation();

        firestation.setAddress(firestationUpdateDTO.getAddress());
        firestation.setStation(firestationUpdateDTO.getStation());

        return firestation;
    }

    @Override
    public Firestation toResponseDTO(Firestation firestation) {
        if (firestation == null) {
            return null;
        }

        Firestation firestation1 = new Firestation();

        firestation1.setAddress(firestation.getAddress());
        firestation1.setStation(firestation.getStation());

        return firestation1;
    }

    @Override
    public Firestation toEntityFromResponseDTO(Firestation firestationResponseDTO) {
        if (firestationResponseDTO == null) {
            return null;
        }

        Firestation firestation = new Firestation();

        firestation.setAddress(firestationResponseDTO.getAddress());
        firestation.setStation(firestationResponseDTO.getStation());

        return firestation;
    }
}
