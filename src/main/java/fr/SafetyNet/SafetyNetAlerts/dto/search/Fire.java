package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;

import fr.SafetyNet.SafetyNetAlerts.dto.person.PersonForFire;
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
public class Fire {

    private List<Integer> stations;

    private List<PersonForFire> persons;

}
