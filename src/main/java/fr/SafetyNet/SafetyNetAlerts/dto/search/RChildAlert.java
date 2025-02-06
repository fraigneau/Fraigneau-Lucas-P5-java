package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForChildAlert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RChildAlert {

    List<RPersonForChildAlert> children;

}
