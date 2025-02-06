package fr.SafetyNet.SafetyNetAlerts.dto.person;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonForFirestationCoverage {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}