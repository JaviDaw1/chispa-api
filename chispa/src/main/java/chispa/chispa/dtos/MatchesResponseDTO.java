package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MatchesResponseDTO {
    private final Long id;
    private final Users user1;
    private final Users user2;
    private final LocalDate matchDate;
    private final String matchState;
}
