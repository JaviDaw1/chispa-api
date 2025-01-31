package chispa.chispa.mappers;

import chispa.chispa.dtos.MessagesRequestDTO;
import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.models.Messages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MessagesMapper {
    public MessagesResponseDTO toResponse(Messages messages) {
        return new MessagesResponseDTO(
                messages.getId(),
                messages.getMatch(),
                messages.getSenderUser(),
                messages.getReceiverUser(),
                messages.getContent(),
                messages.getTimestamp(),
                messages.getIsRead()
        );
    }
    public List<MessagesResponseDTO> toResponse(List<Messages> messagess) {
        return messagess.stream()
                .map(this::toResponse)
                .toList();
    }
    public Messages toModel(MessagesRequestDTO messagesRequestDto) {
        return new Messages(
                null,
                messagesRequestDto.getMatch(),
                messagesRequestDto.getSenderUser(),
                messagesRequestDto.getReceiverUser(),
                messagesRequestDto.getContent(),
                LocalDateTime.now(),
                messagesRequestDto.getIsRead()
        );
    }
}
