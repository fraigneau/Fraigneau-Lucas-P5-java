package fr.SafetyNet.SafetyNetAlerts.mapper;

import org.springframework.stereotype.Component;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import fr.SafetyNet.SafetyNetAlerts.util.Generated;

@Generated
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toCreateDTO(Person person) {
        if (person == null) {
            return null;
        }

        Person person1 = new Person();

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setCity(person.getCity());
        person1.setZip(person.getZip());
        person1.setPhone(person.getPhone());
        person1.setEmail(person.getEmail());

        return person1;
    }

    @Override
    public Person toEntityFromCreateDTO(Person personCreateDTO) {
        if (personCreateDTO == null) {
            return null;
        }

        Person person = new Person();

        person.setFirstName(personCreateDTO.getFirstName());
        person.setLastName(personCreateDTO.getLastName());
        person.setAddress(personCreateDTO.getAddress());
        person.setCity(personCreateDTO.getCity());
        person.setZip(personCreateDTO.getZip());
        person.setPhone(personCreateDTO.getPhone());
        person.setEmail(personCreateDTO.getEmail());

        return person;
    }

    @Override
    public Person toUpdateDTO(Person person) {
        if (person == null) {
            return null;
        }

        Person person1 = new Person();

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setCity(person.getCity());
        person1.setZip(person.getZip());
        person1.setPhone(person.getPhone());
        person1.setEmail(person.getEmail());

        return person1;
    }

    @Override
    public Person toEntityFromUpdateDTO(Person personUpdateDTO) {
        if (personUpdateDTO == null) {
            return null;
        }

        Person person = new Person();

        person.setFirstName(personUpdateDTO.getFirstName());
        person.setLastName(personUpdateDTO.getLastName());
        person.setAddress(personUpdateDTO.getAddress());
        person.setCity(personUpdateDTO.getCity());
        person.setZip(personUpdateDTO.getZip());
        person.setPhone(personUpdateDTO.getPhone());
        person.setEmail(personUpdateDTO.getEmail());

        return person;
    }

    @Override
    public Person toResponseDTO(Person person) {
        if (person == null) {
            return null;
        }

        Person person1 = new Person();

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setCity(person.getCity());
        person1.setZip(person.getZip());
        person1.setPhone(person.getPhone());
        person1.setEmail(person.getEmail());

        return person1;
    }

    @Override
    public Person toEntityFromResponseDTO(Person personResponseDTO) {
        if (personResponseDTO == null) {
            return null;
        }

        Person person = new Person();

        person.setFirstName(personResponseDTO.getFirstName());
        person.setLastName(personResponseDTO.getLastName());
        person.setAddress(personResponseDTO.getAddress());
        person.setCity(personResponseDTO.getCity());
        person.setZip(personResponseDTO.getZip());
        person.setPhone(personResponseDTO.getPhone());
        person.setEmail(personResponseDTO.getEmail());

        return person;
    }

    @Override
    public RPersonForFirestationCoverage toPersonForFirestationCoverageResponseDTO(Person person) {
        if (person == null) {
            return null;
        }

        RPersonForFirestationCoverage rPersonForFirestationCoverage = new RPersonForFirestationCoverage();

        rPersonForFirestationCoverage.setFirstName(person.getFirstName());
        rPersonForFirestationCoverage.setLastName(person.getLastName());
        rPersonForFirestationCoverage.setAddress(person.getAddress());
        rPersonForFirestationCoverage.setPhone(person.getPhone());

        return rPersonForFirestationCoverage;
    }
}
