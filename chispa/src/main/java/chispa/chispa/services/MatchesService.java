package chispa.chispa.services;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;

import java.util.List;

public interface MatchesService {
    Matches findById(Long id);

    Matches save(Matches match);

    Matches update(Long id, Matches match);

    void deleteById(Long id);

    List<Matches> findAll();

    List<Matches> findByUserId(Long userId);

    Long countAcceptedMatches();

    Long countRejectedMatches();

    Matches patch(Long id, Matches matches);
}
