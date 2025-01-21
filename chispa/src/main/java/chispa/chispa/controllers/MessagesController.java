package chispa.chispa.controllers;

import chispa.chispa.dtos.MessagesRequestDTO;
import chispa.chispa.dtos.MessagesResponseDTO;
import chispa.chispa.mappers.MessagesMapper;
import chispa.chispa.models.Messages;
import chispa.chispa.services.MessagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MessagesController {
    private final MessagesService messagesService;
    private final MessagesMapper messagesMapper;

    @GetMapping("")
    public ResponseEntity<List<MessagesResponseDTO>> getAllMessages() {
        log.info("getAllMessages");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> getMessageById(@PathVariable Long id) {
        log.info("getMessageById");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<MessagesResponseDTO> postMessage(@RequestBody MessagesRequestDTO messagesRequestDto) {
        log.info("addMessage");
        Messages messageSaved = messagesService.save(messagesMapper.toModel(messagesRequestDto));
        return ResponseEntity.created(null).body(messagesMapper.toResponse(messageSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> putMessage(@PathVariable Long id, @RequestBody MessagesRequestDTO messagesRequestDto) {
        log.info("putMessage");
        Messages messageUpdated = messagesService.update(id, messagesMapper.toModel(messagesRequestDto));
        return ResponseEntity.ok(messagesMapper.toResponse(messageUpdated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MessagesResponseDTO> patchMessage(@PathVariable Long id, @RequestBody MessagesRequestDTO messagesRequestDto) {
        log.info("patchMessage");
        Messages messagePatched = messagesService.patch(id, messagesMapper.toModel(messagesRequestDto));
        return ResponseEntity.ok(messagesMapper.toResponse(messagePatched));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.info("deleteMessage");
        messagesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<MessagesResponseDTO>> getMessagesByMatchId(@PathVariable Long matchId) {
        log.info("getMessagesByMatchId");
        return ResponseEntity.ok(messagesMapper.toResponse(messagesService.findByMatchId(matchId)));
    }
}
