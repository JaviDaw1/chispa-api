package chispa.chispa.services;

import chispa.chispa.models.Matches;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.MatchState;
import chispa.chispa.repositories.MatchesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor

public class MatchesServiceImpl implements MatchesService {
    private final MatchesRepository matchesRepository;
    private final UsersDetailsRepository usersRepository;

    @Override
    public Matches findById(Long id) {
        matchesRepository.findById(id);
        return matchesRepository.findById(id).get();
    }

    @Override
    public Matches save(Matches match) {
        match.setUser1(usersRepository.findById(match.getUser1().getId()).get());
        match.setUser2(usersRepository.findById(match.getUser2().getId()).get());
        return matchesRepository.save(match);
    }

    @Override
    public Matches update(Long id, Matches match) {
        Matches updated = this.findById(id);
        updated.setMatchState(match.getMatchState());
        updated.setUser1(match.getUser1());
        updated.setUser2(match.getUser2());
        updated.setMatchDate(match.getMatchDate());
        return matchesRepository.save(updated);
    }

    @Override
    public void deleteById(Long id) {
        matchesRepository.deleteById(id);
    }

    @Override
    public List<Matches> findAll() {
        return matchesRepository.findAll();
    }

    @Override
    public List<Matches> findByUserId(Long userId) {
        return matchesRepository.findByEitherUserId(userId);
    }

    @Override
    public List<Matches> findMatchesByMatchState(MatchState matchState) {
        return matchesRepository.findMatchesByMatchState(matchState);
    }

    @Override
    public Long countMatchesByMatchState(MatchState matchState) {
        return matchesRepository.countByMatchState(matchState);
    }

    @Override
    public Matches patch(Long id, Matches matches) {
        Matches matchToPatch = matchesRepository.findById(id).orElseThrow();
        if (matches.getMatchState() != null) {
            matchToPatch.setMatchState(matches.getMatchState());
        }
        if (matches.getMatchDate() != null) {
            matchToPatch.setMatchDate(matches.getMatchDate());
        }
        return matchesRepository.save(matchToPatch);
    }


}
