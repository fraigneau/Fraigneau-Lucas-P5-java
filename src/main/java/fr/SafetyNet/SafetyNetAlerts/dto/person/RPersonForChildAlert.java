package fr.SafetyNet.SafetyNetAlerts.dto.person;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonForChildAlert {

    private String firstName;
    private String lastName;
    private int age;

    private List<Person> relatives;

}
