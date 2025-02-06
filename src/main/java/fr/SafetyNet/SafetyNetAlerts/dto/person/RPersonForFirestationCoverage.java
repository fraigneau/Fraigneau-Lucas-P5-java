package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonForFirestationCoverage {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}