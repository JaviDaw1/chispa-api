package chispa.chispa.repositories;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Repository for managing user profiles.
 */
public interface ProfilesRepository extends JpaRepository<Profile, Long> {

    /**
     * Finds a profile by the associated user ID.
     */
    Profile findByUserId(Long userId);

    /**
     * Finds profiles by gender.
     */
    List<Profile> findProfileByGender(Gender gender);

    /**
     * Counts the number of profiles for each preferred relationship type.
     */
    @Query("SELECT p.preferredRelationship, COUNT(p) FROM Profile p GROUP BY p.preferredRelationship")
    Map<PreferredRelationship, Long> countProfilesByPreferredRelationship(PreferredRelationship preferredRelationship);
}
