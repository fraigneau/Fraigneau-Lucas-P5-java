package fr.SafetyNet.SafetyNetAlerts.model;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;
import lombok.Data;

@Data
@NotTested
public class Firestation {

    private String address;
    private int station;

    public Firestation() {
    }
}
