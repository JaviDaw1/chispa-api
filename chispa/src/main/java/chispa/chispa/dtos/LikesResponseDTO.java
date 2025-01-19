package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LikesResponseDTO {
    private final Long id;
    private final Users liker;
    private final Users liked;
    private final LocalDate timestamp;
    private final String state;
}
