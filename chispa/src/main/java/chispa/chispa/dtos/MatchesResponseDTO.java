package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MatchesResponseDTO {
    private final Long id;
    private final Users user1;
    private final Users user2;
    private final LocalDateTime matchDate;
    private final MatchState matchState;
}
