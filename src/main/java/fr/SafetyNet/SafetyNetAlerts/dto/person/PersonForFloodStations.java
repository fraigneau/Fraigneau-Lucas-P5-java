package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonForFloodStations {

    private String address;
    private String lastName;
    private String phone;
    private int age;

    private List<String> medications;
    private List<String> allergies;

}
