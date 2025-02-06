package fr.SafetyNet.SafetyNetAlerts.dto.person;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
