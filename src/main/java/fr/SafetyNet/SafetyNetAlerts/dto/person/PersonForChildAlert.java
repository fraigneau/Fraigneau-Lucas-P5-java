package fr.SafetyNet.SafetyNetAlerts.dto.person;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonForChildAlert {

    private String firstName;
    private String lastName;
    private int age;

    private List<Person> relatives;

}
