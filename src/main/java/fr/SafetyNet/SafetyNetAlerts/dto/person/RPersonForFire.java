package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonForFire {

    private String lastName;
    private String phone;
    private int age;

    private List<String> medications;
    private List<String> allergies;

}
