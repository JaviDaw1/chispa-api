package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import lombok.Data;

@Data
public class MatchesRequestDTO {
    private final Users user1;
    private final Users user2;
    private final MatchState matchState; //ENUM: 'pending', 'accepted', 'rejected', 'cancelled'
}
