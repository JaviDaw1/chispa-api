package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.LikeState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikesResponseDTO {
    private final Long id;
    private final Users liker;
    private final Users liked;
    private final LocalDateTime timestamp;
    private final LikeState state;
}
