package chispa.chispa.repositories;

import chispa.chispa.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    Messages findByMessageId(Long messageId);

    Long countByMatchId(Long matchId);

    List<Messages> findByMatchId(Long matchId);


}
