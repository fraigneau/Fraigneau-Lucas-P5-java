package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import fr.SafetyNet.SafetyNetAlerts.dto.person.RPersonForFirestationCoverage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RFirestationCoverage {

    private AtomicInteger adultCount;
    private AtomicInteger childCount;

    private List<RPersonForFirestationCoverage> persons;

}