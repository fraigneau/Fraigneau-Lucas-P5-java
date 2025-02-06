package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonForFloodStations {

    private String address;
    private String lastName;
    private String phone;
    private int age;

    private List<String> medications;
    private List<String> allergies;

}
