package chispa.chispa.controllers;

import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.models.Messages;
import chispa.chispa.services.MessagesService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Controller to handle WebSocket messages for chat functionality.
 * Supports sending and marking messages as read in real-time using STOMP over WebSocket.
 */
@Controller
public class WebSocketMessagesController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessagesService messagesService;

    public WebSocketMessagesController(SimpMessagingTemplate messagingTemplate,
                                       MessagesService messagesService) {
        this.messagingTemplate = messagingTemplate;
        this.messagesService = messagesService;
    }

    /**
     * Endpoint to send a chat message to a specific match.
     * Saves the message and broadcasts it to subscribers of the topic.
     *
     * @param matchId ID of the chat match
     * @param message Message object sent by client
     * @return DTO of saved message
     */
    @MessageMapping("/chat/{matchId}/send")
    @SendTo("/topic/chat/{matchId}")
    public MessagesResponseDTO sendMessage(@DestinationVariable Long matchId,
                                           Messages message) {
        Messages savedMessage = messagesService.save(message);
        return convertToDto(savedMessage);
    }

    /**
     * Endpoint to mark a message as read.
     * Updates the message status and notifies subscribers of the update.
     * @param matchId ID of the chat match
     * @param messageId ID of the message to mark as read
     */
    @MessageMapping("/chat/{matchId}/markAsRead")
    public void markMessageAsRead(@DestinationVariable Long matchId,
                                  Long messageId) {
        Messages message = messagesService.markAsRead(messageId);
        messagingTemplate.convertAndSend(
                "/topic/chat/" + matchId + "/updates",
                convertToDto(message)
        );
    }

    /**
     * Helper method to convert Message entity to MessagesResponseDTO.
     * @param message Message entity
     * @return MessagesResponseDTO
     */
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