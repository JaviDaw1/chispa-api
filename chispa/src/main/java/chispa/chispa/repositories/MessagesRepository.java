package chispa.chispa.repositories;

import chispa.chispa.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for managing chat messages.
 */
public interface MessagesRepository extends JpaRepository<Messages, Long> {

    /**
     * Finds all messages in a match, ordered by timestamp (oldest first).
     */
    List<Messages> findByMatchIdOrderByTimestampAsc(Long matchId);

    /**
     * Gets direct messages sent from one user to another.
     */
    List<Messages> findBySenderUserIdAndReceiverUserId(Long senderId, Long receiverId);

    /**
     * Deletes all messages related to a match.
     */
    void deleteMessagesByMatchId(Long matchId);
}
