package fr.SafetyNet.SafetyNetAlerts.mapper;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
public interface PersonMapper {

    Person toCreateDTO(Person person);

    Person toEntityFromCreateDTO(Person personCreateDTO);

    Person toUpdateDTO(Person person);

    Person toEntityFromUpdateDTO(Person personUpdateDTO);

    Person toResponseDTO(Person person);

    Person toEntityFromResponseDTO(Person personResponseDTO);

    RPersonForFirestationCoverage toPersonForFirestationCoverageResponseDTO(Person person);

}
