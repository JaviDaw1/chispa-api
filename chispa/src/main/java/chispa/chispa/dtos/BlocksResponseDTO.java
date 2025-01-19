package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BlocksResponseDTO {
    private final Long id;
    private final Users reporter;
    private final Users reported;
    private final LocalDate blockDate;
    private final String blockReason;
}
