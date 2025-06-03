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

/**
 * Implementation of the MessagesService interface.
 * Handles business logic for user messages and validation.
 */
@Transactional
@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    // Repository dependencies
    private final MessagesRepository messagesRepository;
    private final UsersDetailsRepository usersRepository;
    private final MatchesRepository matchesRepository;

    @Override
    public Messages findById(Long id) {
        // Find message or throw exception if not found
        return messagesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Message not found"));
    }

    @Override
    public Messages save(Messages message) {
        // Validate sender, receiver and match exist
        Users sender = usersRepository.findById(message.getSenderUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        Users receiver = usersRepository.findById(message.getReceiverUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Matches match = matchesRepository.findById(message.getMatch().getId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found"));

        // Verify users belong to this match
        if (!isUsersInMatch(match, sender.getId(), receiver.getId())) {
            throw new IllegalArgumentException("Users don't belong to this match");
        }

        // Set message timestamp and default read status
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
        // Find existing message and update its fields
        Messages existing = findById(id);

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
    public void deleteMessagesByMatchId(Long matchId) {
        messagesRepository.deleteMessagesByMatchId(matchId);
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
    public Messages markAsRead(Long messageId) {
        // Mark message as read
        Messages message = findById(messageId);
        message.setIsRead(true);
        return messagesRepository.save(message);
    }

    /**
     * Checks if two users belong to a match.
     *
     * @param match   the match to check
     * @param userId1 the first user ID
     * @param userId2 the second user ID
     * @return true if users belong to this match, false otherwise
     */
    private boolean isUsersInMatch(Matches match, Long userId1, Long userId2) {
        Long matchUser1 = match.getUser1().getId();
        Long matchUser2 = match.getUser2().getId();

        // Check both possible orderings of users in the match
        return (matchUser1.equals(userId1) && matchUser2.equals(userId2)) ||
                (matchUser1.equals(userId2) && matchUser2.equals(userId1));
    }
}