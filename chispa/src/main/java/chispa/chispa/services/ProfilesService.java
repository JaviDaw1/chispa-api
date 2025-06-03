package chispa.chispa.services;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;
import java.util.List;

/**
 * Service interface for managing user profiles.
 * Provides methods for CRUD operations and profile management.
 */
public interface ProfilesService {
    /**
     * Finds a profile by its ID.
     *
     * @param id the profile ID
     * @return the found profile
     */
    Profile findById(Long id);

    /**
     * Finds a profile by user ID.
     * @param userId the user ID
     * @return the found profile
     */
    Profile findByUserId(Long userId);

    /**
     * Saves a new profile.
     * @param profile the profile to save
     * @return the saved profile
     */
    Profile save(Profile profile);

    /**
     * Updates an existing profile.
     * @param id the ID of the profile to update
     * @param profile the updated profile data
     * @return the updated profile
     */
    Profile update(Long id, Profile profile);

    /**
     * Deletes a profile by its ID.
     * @param id the profile ID to delete
     */
    void deleteById(Long id);

    /**
     * Retrieves all profiles.
     * @return list of all profiles
     */
    List<Profile> findAll();

    /**
     * Finds profiles by gender.
     * @param gender the gender to filter by
     * @return list of profiles
     */
    List<Profile> findProfilesByGender(Gender gender);

    /**
     * Sets a profile's online status to true.
     * @param profile the profile to update
     * @return the updated profile
     */
    Profile setOnline(Profile profile);

    /**
     * Sets a profile's online status to false.
     * @param profile the profile to update
     * @return the updated profile
     */
    Profile setOffline(Profile profile);
}