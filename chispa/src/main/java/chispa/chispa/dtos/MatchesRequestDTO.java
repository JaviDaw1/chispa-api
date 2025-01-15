package chispa.chispa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchesRequestDTO {
    private Long user1Id;
    private Long user2Id;
    private String matchState; //ENUM: 'pending', 'accepted', 'rejected', 'cancelled'
}
