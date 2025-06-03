package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for block response data.
 */
@Data
public class BlocksResponseDTO {
    /**
     * Unique identifier
     */
    private final Long id;

    /** User who reported */
    private final Users reporter;

    /** User who was reported */
    private final Users reported;

    /** Date and time when block occurred */
    private final LocalDateTime blockDate;

    /** Reason given for the block */
    private final String blockReason;
}
