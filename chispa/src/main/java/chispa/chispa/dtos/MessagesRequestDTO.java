package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessagesRequestDTO {
    @NotNull(message = "El match no puede estar vacío")
    private Matches match;

    @NotNull(message = "El usuario remitente no puede estar vacío")
    private Users senderUser;

    @NotNull(message = "El usuario destinatario no puede estar vacío")
    private Users receiverUser;

    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    @Size(max = 2000, message = "El contenido del mensaje no puede tener más de 2000 caracteres")
    private String content;

    @NotNull(message = "El estado de lectura no puede estar vacío")
    private Boolean isRead;
}
