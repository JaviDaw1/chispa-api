package chispa.chispa.controllers;

import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.models.Messages;
import chispa.chispa.services.MessagesService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessagesController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessagesService messagesService;

    public WebSocketMessagesController(SimpMessagingTemplate messagingTemplate,
                                       MessagesService messagesService) {
        this.messagingTemplate = messagingTemplate;
        this.messagesService = messagesService;
    }

    @MessageMapping("/chat/{matchId}/send")
    @SendTo("/topic/chat/{matchId}")
    public MessagesResponseDTO sendMessage(@DestinationVariable Long matchId,
                                           Messages message) {
        Messages savedMessage = messagesService.save(message);
        return convertToDto(savedMessage);
    }

    @MessageMapping("/chat/{matchId}/markAsRead")
    public void markMessageAsRead(@DestinationVariable Long matchId,
                                  Long messageId) {
        Messages message = messagesService.markAsRead(messageId);
        messagingTemplate.convertAndSend(
                "/topic/chat/" + matchId + "/updates",
                convertToDto(message)
        );
    }

    private MessagesResponseDTO convertToDto(Messages message) {
        return new MessagesResponseDTO(
                message.getId(),
                message.getMatch(),
                message.getSenderUser(),
                message.getReceiverUser(),
                message.getContent(),
                message.getTimestamp(),
                message.getIsRead(),
                message.getMessageState()
        );
    }
}