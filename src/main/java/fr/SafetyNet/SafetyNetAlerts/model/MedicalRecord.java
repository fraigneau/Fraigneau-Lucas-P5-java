package fr.SafetyNet.SafetyNetAlerts.model;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.util.Generated;
import lombok.Data;

@Data
@Generated
public class Medicalrecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public Medicalrecord() {
    }
}
