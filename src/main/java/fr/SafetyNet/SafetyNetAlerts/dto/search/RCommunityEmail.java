package fr.SafetyNet.SafetyNetAlerts.dto.search;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RCommunityEmail {

    private List<String> emails;

}
