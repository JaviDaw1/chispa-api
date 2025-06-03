package chispa.chispa.dtos;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MessageState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for message request data.
 */
@Data
public class MessagesRequestDTO {
    /**
     * Match to which this message belongs
     */
    @NotNull(message = "El match no puede estar vacío")
    private Matches match;

    /** User sending the message */
    @NotNull(message = "El usuario remitente no puede estar vacío")
    private Users senderUser;

    /** User receiving the message */
    @NotNull(message = "El usuario destinatario no puede estar vacío")
    private Users receiverUser;

    /** Message content */
    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    @Size(max = 2000, message = "El contenido del mensaje no puede tener más de 2000 caracteres")
    private String content;

    /** Whether the message has been read */
    @NotNull(message = "El estado de lectura no puede estar vacío")
    private Boolean isRead;

    /** State of the message */
    @NotNull(message = "El estado del mensaje no puede estar vacío")
    private MessageState messageState;
}
