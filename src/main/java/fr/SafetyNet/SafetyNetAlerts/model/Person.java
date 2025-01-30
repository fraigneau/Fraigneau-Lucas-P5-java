package fr.SafetyNet.SafetyNetAlerts.model;

import fr.SafetyNet.SafetyNetAlerts.util.Generated;
import lombok.Data;

@Data
@Generated
public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    public Person() {
    }

}
