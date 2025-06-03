package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.LikeState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for like response data.
 */
@Data
public class LikesResponseDTO {
    /**
     * Unique identifier
     */
    private final Long id;

    /** User who liked */
    private final Users liker;

    /** User who was liked */
    private final Users liked;

    /** Timestamp when the like was made */
    private final LocalDateTime timestamp;

    /** Current state of the like */
    private final LikeState state;
}
