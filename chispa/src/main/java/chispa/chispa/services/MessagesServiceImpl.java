package chispa.chispa.services;

import chispa.chispa.models.Messages;
import chispa.chispa.repositories.MessagesRepository;
import chispa.chispa.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;
    private final UsersRepository usersRepository;

    @Override
    public Messages findById(Long id) {
        return messagesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Messages not found"));
    }

    @Override
    public Messages save(Messages message) {
        message.setSenderUser(usersRepository.findById(message.getSenderUser().getId()).orElseThrow());
        message.setReceiverUser(usersRepository.findById(message.getSenderUser().getId()).orElseThrow());
        return messagesRepository.save(message);
    }

    @Override
    public Messages update(Long id, Messages message) {
        Messages updated = this.findById(id);
        updated.setContent(message.getContent());
        updated.setIsRead(message.getIsRead());
        return messagesRepository.save(updated);
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
        return messagesRepository.findByMatchId(matchId);
    }

    @Override
    public Messages patch(Long id, Messages message) {
        Messages messageToPatch = messagesRepository.findById(id).orElseThrow();
        if (message.getContent() != null) {
            messageToPatch.setContent(message.getContent());
        }
        if (message.getIsRead() != null) {
            messageToPatch.setIsRead(message.getIsRead());
        }
        return messagesRepository.save(messageToPatch);
    }
}
