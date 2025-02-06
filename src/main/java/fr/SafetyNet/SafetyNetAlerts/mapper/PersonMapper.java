package fr.SafetyNet.SafetyNetAlerts.mapper;

import org.mapstruct.Mapper;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toCreateDTO(Person person);

    Person toEntityFromCreateDTO(Person personCreateDTO);

    Person toUpdateDTO(Person person);

    Person toEntityFromUpdateDTO(Person personUpdateDTO);

    Person toResponseDTO(Person person);

    Person toEntityFromResponseDTO(Person personResponseDTO);

    RPersonForFirestationCoverage toPersonForFirestationCoverageResponseDTO(Person person);

}
