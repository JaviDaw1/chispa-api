package chispa.chispa.services;

import chispa.chispa.models.Preferences;
import java.util.List;

/**
 * Service interface for managing user preferences.
 * Provides methods for CRUD operations on user preferences.
 */
public interface PreferencesService {
    /**
     * Finds preferences by ID.
     *
     * @param id the preferences ID
     * @return the found preferences
     */
    Preferences findById(Long id);

    /**
     * Finds preferences by user ID.
     * @param userId the user ID
     * @return the found preferences
     */
    Preferences findByUserId(Long userId);

    /**
     * Saves new preferences.
     * @param preferences the preferences to save
     * @return the saved preferences
     */
    Preferences save(Preferences preferences);

    /**
     * Updates existing preferences.
     * @param id the ID of the preferences to update
     * @param preferences the updated preferences data
     * @return the updated preferences
     */
    Preferences update(Long id, Preferences preferences);

    /**
     * Deletes preferences by ID.
     * @param id the preferences ID to delete
     */
    void deleteById(Long id);

    /**
     * Retrieves all preferences.
     * @return list of all preferences
     */
    List<Preferences> findAll();
}