package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.dto.person.PersonForFloodStations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FloodStations {

    List<PersonForFloodStations> persons;

}
