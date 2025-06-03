package chispa.chispa.mappers;

import chispa.chispa.dtos.MessagesRequestDTO;
import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.models.Messages;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper class for converting between Messages entity and DTOs.
 */
@Component
public class MessagesMapper {

    /**
     * Converts a Messages entity to a MessagesResponseDTO.
     *
     * @param messages the Messages entity to convert
     * @return the corresponding MessagesResponseDTO
     */
    public MessagesResponseDTO toResponse(Messages messages) {
        // Map entity fields to response DTO
        return new MessagesResponseDTO(
                messages.getId(),
                messages.getMatch(),
                messages.getSenderUser(),
                messages.getReceiverUser(),
                messages.getContent(),
                messages.getTimestamp(),
                messages.getIsRead(),
                messages.getMessageState()
        );
    }

    /**
     * Converts a list of Messages entities to a list of MessagesResponseDTOs.
     * @param messagess list of Messages entities
     * @return list of MessagesResponseDTOs
     */
    public List<MessagesResponseDTO> toResponse(List<Messages> messagess) {
        // Stream the list and map each Messages entity to DTO
        return messagess.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Converts a MessagesRequestDTO to a Messages entity.
     * @param messagesRequestDto the request DTO to convert
     * @return the new Messages entity
     */
    public Messages toModel(MessagesRequestDTO messagesRequestDto) {
        // Create new Messages entity with current timestamp
        return new Messages(
                null,
                messagesRequestDto.getMatch(),
                messagesRequestDto.getSenderUser(),
                messagesRequestDto.getReceiverUser(),
                messagesRequestDto.getContent(),
                LocalDateTime.now(),
                messagesRequestDto.getIsRead(),
                messagesRequestDto.getMessageState()
        );
    }
}
