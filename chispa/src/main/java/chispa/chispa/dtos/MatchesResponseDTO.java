package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for match response data.
 */
@Data
public class MatchesResponseDTO {
    /**
     * Unique identifier
     */
    private final Long id;

    /** First user in the match */
    private final Users user1;

    /** Second user in the match */
    private final Users user2;

    /** Date and time when match was created */
    private final LocalDateTime matchDate;

    /** Current state of the match */
    private final MatchState matchState;
}
