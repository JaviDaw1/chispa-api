package chispa.chispa.services;

import chispa.chispa.models.Preferences;
import chispa.chispa.repositories.PreferencesRepository;
import chispa.chispa.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class PreferencesServiceImpl implements PreferencesService {
    private final PreferencesRepository preferencesRepository;
    private final UsersRepository usersRepository;

    @Override
    public Preferences findById(Long id) {
        return preferencesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Preferences not found"));
    }

    @Override
    public Preferences findByUserId(Long userId) {
        return preferencesRepository.findByUserId(userId);
    }

    @Override
    public Preferences save(Preferences preferences) {
        preferences.setUser(usersRepository.findById(preferences.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Preferences not found")));
        return preferencesRepository.save(preferences);
    }

    @Override
    public Preferences update(Long id, Preferences preferences) {
        Preferences updatedPreferences = this.findById(id);
        updatedPreferences.setUser(preferences.getUser());
        updatedPreferences.setFavoriteGender(preferences.getFavoriteGender());
        updatedPreferences.setMaxDistance(preferences.getMaxDistance());
        updatedPreferences.setMinAgeRange(preferences.getMinAgeRange());
        updatedPreferences.setMaxAgeRange(preferences.getMaxAgeRange());
        return preferencesRepository.save(updatedPreferences);
    }

    @Override
    public void deleteById(Long id) {
        preferencesRepository.deleteById(id);
    }

    @Override
    public List<Preferences> findAll() {
        return preferencesRepository.findAll();
    }
}
