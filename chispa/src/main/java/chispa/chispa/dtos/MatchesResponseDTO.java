package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchesResponseDTO {
    private Long id;
    private Long user1Id;
    private Long user2Id;
    private String matchDate;
    private String matchState;
}
