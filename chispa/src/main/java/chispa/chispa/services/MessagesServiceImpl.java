package chispa.chispa.services;

import chispa.chispa.models.Messages;
import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import chispa.chispa.repositories.MatchesRepository;
import chispa.chispa.repositories.MessagesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;
    private final UsersDetailsRepository usersRepository;
    private final MatchesRepository matchesRepository;

    @Override
    public Messages findById(Long id) {
        return messagesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));
    }

    @Override
    public Messages save(Messages message) {
        Users sender = usersRepository.findById(message.getSenderUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Remitente no encontrado"));
        Users receiver = usersRepository.findById(message.getReceiverUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado"));

        Matches match = matchesRepository.findById(message.getMatch().getId())
                .orElseThrow(() -> new IllegalArgumentException("Match no encontrado"));

        if (!isUsersInMatch(match, sender.getId(), receiver.getId())) {
            throw new IllegalArgumentException("Los usuarios no pertenecen a este match");
        }

        message.setTimestamp(LocalDateTime.now());

        if (message.getIsRead() == null) {
            message.setIsRead(false);
        }
        message.setSenderUser(sender);
        message.setReceiverUser(receiver);
        message.setMatch(match);
        return messagesRepository.save(message);
    }

    @Override
    public Messages update(Long id, Messages message) {
        Messages existing = findById(id);

        // Solo permitir actualizar contenido y estado de lectura
        if (message.getContent() != null) {
            existing.setContent(message.getContent());
        }
        if (message.getIsRead() != null) {
            existing.setIsRead(message.getIsRead());
        }

        return messagesRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        messagesRepository.deleteById(id);
    }

    @Override
    public List<Messages> findAll() {
        return messagesRepository.findAll();
    }

    @Override
    public List<Messages> findByMatchId(Long matchId) {
        return messagesRepository.findByMatchIdOrderByTimestampAsc(matchId);
    }


    @Override
    public List<Messages> getConversation(Long matchId, Long userId1, Long userId2) {
        Matches match = matchesRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match no encontrado"));

        if (!isUsersInMatch(match, userId1, userId2)) {
            throw new IllegalArgumentException("Los usuarios no pertenecen a este match");
        }

        return messagesRepository.findByMatchIdOrderByTimestampAsc(matchId);
    }

    @Override
    public Messages markAsRead(Long messageId) {
        Messages message = findById(messageId);
        message.setIsRead(true);
        return messagesRepository.save(message);
    }

    @Override
    public Long countUnreadMessages(Long userId, Long matchId) {
        return messagesRepository.countByReceiverUserIdAndMatchIdAndIsReadFalse(userId, matchId);
    }

    // Método auxiliar para validar usuarios en un match
    private boolean isUsersInMatch(Matches match, Long userId1, Long userId2) {
        Long matchUser1 = match.getUser1().getId();
        Long matchUser2 = match.getUser2().getId();

        return (matchUser1.equals(userId1) && matchUser2.equals(userId2)) ||
                (matchUser1.equals(userId2) && matchUser2.equals(userId1));
    }
}