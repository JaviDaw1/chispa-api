package chispa.chispa.services;

import chispa.chispa.models.Preferences;
import chispa.chispa.repositories.PreferencesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the PreferencesService interface.
 * Handles business logic for user preferences.
 */
@Transactional
@Service
@AllArgsConstructor
public class PreferencesServiceImpl implements PreferencesService {
    // Repository dependencies
    private final PreferencesRepository preferencesRepository;
    private final UsersDetailsRepository usersRepository;

    @Override
    public Preferences findById(Long id) {
        // Find preferences or throw exception if not found
        return preferencesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Preferences not found"));
    }

    @Override
    public Preferences findByUserId(Long userId) {
        return preferencesRepository.findByUserId(userId);
    }

    @Override
    public Preferences save(Preferences preferences) {
        // Validate user exists
        preferences.setUser(usersRepository.findById(preferences.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Preferences not found")));
        return preferencesRepository.save(preferences);
    }

    @Override
    public Preferences update(Long id, Preferences preferences) {
        // Find existing preferences and update its fields
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