package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.*;

import java.util.List;

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
