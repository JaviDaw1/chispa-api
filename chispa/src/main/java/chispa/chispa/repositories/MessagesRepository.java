package chispa.chispa.repositories;

import chispa.chispa.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    Messages findByMessageId(Long messageId);
}
