package chispa.chispa.services;

import chispa.chispa.models.Messages;

import java.util.List;

public interface MessagesService {
    Messages findById(Long id);

    Messages save(Messages message);

    Messages update(Long id, Messages message);

    void deleteById(Long id);

    List<Messages> findAll();

    List<Messages> findByMatchId(Long matchId);

    Long countByMatchId(Long matchId);

    public Messages patch(Long id, Messages message);
}

