package chispa.chispa.repositories;

import chispa.chispa.models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing user preferences.
 */
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    /**
     * Finds preferences by user ID.
     */
    Preferences findByUserId(Long userId);
}
