package chispa.chispa.repositories;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProfilesRepository extends JpaRepository<Profile, Long> {
    Profile findByUserId(Long userId);

    List<Profile> findProfileByGender(Gender gender);

    @Query("SELECT p.preferredRelationship, COUNT(p) FROM Profile p GROUP BY p.preferredRelationship")
    Map<PreferredRelationship, Long> countProfilesByPreferredRelationship(PreferredRelationship preferredRelationship);
}
