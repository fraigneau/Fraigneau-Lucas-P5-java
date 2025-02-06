package fr.SafetyNet.SafetyNetAlerts.model;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;
import lombok.Data;

@Data
@NotTested
public class Medicalrecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public Medicalrecord() {
    }
}
