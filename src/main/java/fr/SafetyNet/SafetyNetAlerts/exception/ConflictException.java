package fr.SafetyNet.SafetyNetAlerts.exception;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;

@NotTested
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

}
