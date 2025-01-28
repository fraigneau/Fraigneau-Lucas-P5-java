package fr.SafetyNet.SafetyNetAlerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FireStation {

    private String address;
    private String station;

    public FireStation() {
    }
}
