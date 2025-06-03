package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.LikeState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for like request data.
 */
@Data
public class LikesRequestDTO {
    /**
     * User who likes
     */
    @NotNull(message = "El usuario que da like no puede estar vacío")
    private Users liker;

    /** User who receives the like */
    @NotNull(message = "El usuario que recibe like no puede estar vacío")
    private Users liked;

    /** State of the like */
    @NotNull(message = "El estado del like no puede estar vacío")
    private LikeState state;
}
