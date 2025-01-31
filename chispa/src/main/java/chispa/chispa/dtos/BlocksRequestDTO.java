package chispa.chispa.dtos;

import chispa.chispa.models.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlocksRequestDTO {
    @NotNull(message = "El usuario que reporta no puede estar vacío")
    private Users reporter;

    @NotNull(message = "El usuario reportado no puede estar vacío")
    private Users reported;

    @Size(max = 500, message = "El motivo del bloqueo no puede tener más de 500 caracteres")
    private String blockReason;
}
