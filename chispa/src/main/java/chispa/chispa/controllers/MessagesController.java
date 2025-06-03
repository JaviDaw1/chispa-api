package chispa.chispa.controllers;

import chispa.chispa.dtos.MessagesRequestDTO;
import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.mappers.MessagesMapper;
import chispa.chispa.models.Messages;
import chispa.chispa.services.MessagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Message operations via REST API.
 * Handles CRUD operations and real-time messaging for chat messages.
 */
@RestController
@RequestMapping("/api/messages")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MessagesController {
    // Service for Message business logic
    private final MessagesService messagesService;
    // Mapper for converting between entity and DTO
    private final MessagesMapper messagesMapper;
    // Template for sending WebSocket messages
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Get all messages in the system.
     *
     * @return List of MessagesResponseDTO
     */
    @GetMapping("")
    public ResponseEntity<List<MessagesResponseDTO>> getAllMessages() {
        log.info("getAllMessages");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findAll()));
    }

    /**
     * Get a message by its ID.
     * @param id Message ID
     * @return MessagesResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> getMessageById(@PathVariable Long id) {
        log.info("getMessageById");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findById(id)));
    }

    /**
     * Create a new message and send it via WebSocket.
     * @param messagesRequestDto Message data
     * @return Created MessagesResponseDTO
     */
    @PostMapping
    public ResponseEntity<MessagesResponseDTO> postMessage(@RequestBody MessagesRequestDTO messagesRequestDto) {
        log.info("addMessage");
        Messages messageSaved = messagesService.save(messagesMapper.toModel(messagesRequestDto));
        messagingTemplate.convertAndSend(
                "/topic/chat/" + messageSaved.getMatch().getId(),
                messagesMapper.toResponse(messageSaved)
        );
        return ResponseEntity.created(null).body(messagesMapper.toResponse(messageSaved));
    }

    /**
     * Update a message and notify via WebSocket.
     * @param id Message ID
     * @param messagesRequestDto Updated data
     * @return Updated MessagesResponseDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> putMessage(@PathVariable Long id, @RequestBody MessagesRequestDTO messagesRequestDto) {
        log.info("putMessage");
        Messages messageUpdated = messagesService.update(id, messagesMapper.toModel(messagesRequestDto));
        messagingTemplate.convertAndSend(
                "/topic/chat/" + messageUpdated.getMatch().getId() + "/updates",
                messagesMapper.toResponse(messageUpdated)
        );
        return ResponseEntity.ok(messagesMapper.toResponse(messageUpdated));
    }

    /**
     * Delete a message by its ID.
     * @param id Message ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.info("deleteMessage");
        messagesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all messages for a specific match.
     * @param matchId Match ID
     */
    @DeleteMapping("/match/{matchId}")
    public ResponseEntity<Void> deleteMessagesByMatchId(@PathVariable Long matchId) {
        log.info("deleteMessagesByMatchId");
        messagesService.deleteMessagesByMatchId(matchId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all messages for a specific match.
     * @param matchId Match ID
     * @return List of MessagesResponseDTO
     */
    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<MessagesResponseDTO>> getMessagesByMatchId(@PathVariable Long matchId) {
        log.info("getMessagesByMatchId");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findByMatchId(matchId)));
    }
}
