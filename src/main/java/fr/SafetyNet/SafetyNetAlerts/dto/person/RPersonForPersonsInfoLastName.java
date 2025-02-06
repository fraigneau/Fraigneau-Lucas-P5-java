package fr.SafetyNet.SafetyNetAlerts.dto.person;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonForPersonsInfoLastName {

    private String lastName;
    private String address;
    private int age;
    private String email;

    private List<String> medications;
    private List<String> allergies;

}
