package chispa.chispa.services;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.models.enums.PreferredRelationship;

import java.util.List;
import java.util.Map;

public interface ProfilesService {
    Profile findById(Long id);

    Profile findByUserId(Long userId);

    Profile save(Profile profile);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);

    List<Profile> findAll();

    Map<PreferredRelationship, Long> countProfilesByPreferredRelationship(PreferredRelationship preferredRelationship);

    List<Profile> findProfilesByGender(Gender gender);
}
