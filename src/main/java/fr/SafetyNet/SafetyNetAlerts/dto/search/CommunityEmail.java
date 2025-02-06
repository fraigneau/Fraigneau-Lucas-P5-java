package fr.SafetyNet.SafetyNetAlerts.dto.search;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommunityEmail {

    private List<String> emails;

}
