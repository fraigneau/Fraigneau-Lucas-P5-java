package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import fr.SafetyNet.SafetyNetAlerts.dto.person.PersonForFirestationCoverage;
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
public class FirestationCoverage {

    private AtomicInteger adultCount;
    private AtomicInteger childCount;

    private List<PersonForFirestationCoverage> persons;

}