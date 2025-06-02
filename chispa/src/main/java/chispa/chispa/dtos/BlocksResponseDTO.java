package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlocksResponseDTO {
    private final Long id;
    private final Users reporter;
    private final Users reported;
    private final LocalDateTime blockDate;
    private final String blockReason;
}
