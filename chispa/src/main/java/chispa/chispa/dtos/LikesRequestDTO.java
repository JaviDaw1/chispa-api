package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.LikeState;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikesRequestDTO {
    @NotNull(message = "El usuario que da like no puede estar vacío")
    private Users liker;

    @NotNull(message = "El usuario que recibe like no puede estar vacío")
    private Users liked;

    @NotNull(message = "El estado del like no puede estar vacío")
    private LikeState state; // ENUM: 'pending', 'accepted', 'rejected'
}
