package fr.SafetyNet.SafetyNetAlerts.dto;

import java.time.LocalDateTime;

import fr.SafetyNet.SafetyNetAlerts.util.NotTested;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@NotTested
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

}
