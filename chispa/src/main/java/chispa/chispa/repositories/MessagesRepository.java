package chispa.chispa.repositories;

import chispa.chispa.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByMatchIdOrderByTimestampAsc(Long matchId);

    List<Messages> findBySenderUserIdAndReceiverUserId(Long senderId, Long receiverId);

    Long countByReceiverUserIdAndMatchIdAndIsReadFalse(Long receiverId, Long matchId);

    List<Messages> findByMatchIdAndReceiverUserIdAndIsReadFalse(Long matchId, Long receiverId);
}