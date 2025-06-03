package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for match request data.
 */
@Data
public class MatchesRequestDTO {
    /**
     * First user in the match
     */
    @NotNull(message = "El primer usuario no puede estar vacío")
    private Users user1;

    /** Second user in the match */
    @NotNull(message = "El segundo usuario no puede estar vacío")
    private Users user2;

    /** Current state of the match */
    @NotNull(message = "El estado del match no puede estar vacío")
    private MatchState matchState;
}
