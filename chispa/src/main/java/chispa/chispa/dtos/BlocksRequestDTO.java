package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for block request data.
 */
@Data
public class BlocksRequestDTO {
    /**
     * User who reports the block
     */
    @NotNull(message = "El usuario que reporta no puede estar vacío")
    private Users reporter;

    /** User who is reported */
    @NotNull(message = "El usuario reportado no puede estar vacío")
    private Users reported;

    /** Reason for blocking (optional, max 500 chars) */
    @Size(max = 500, message = "El motivo del bloqueo no puede tener más de 500 caracteres")
    private String blockReason;
}
