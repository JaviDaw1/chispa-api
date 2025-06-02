package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchesRequestDTO {
    @NotNull(message = "El primer usuario no puede estar vacío")
    private Users user1;

    @NotNull(message = "El segundo usuario no puede estar vacío")
    private Users user2;

    @NotNull(message = "El estado del match no puede estar vacío")
    private MatchState matchState;
}
