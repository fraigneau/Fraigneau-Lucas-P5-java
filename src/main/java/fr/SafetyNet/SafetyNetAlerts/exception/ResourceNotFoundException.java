package fr.SafetyNet.SafetyNetAlerts.exception;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;

@NotTested
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
