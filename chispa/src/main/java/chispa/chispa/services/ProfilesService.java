package chispa.chispa.services;

import chispa.chispa.models.Profile;

import java.util.List;
import java.util.Map;

public interface ProfilesService {
    Profile findById(Long id);

    Profile findByUserId(Long userId);

    Profile save(Profile profile);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);

    List<Profile> findAll();

    Map<String, Long> countProfilesByPreferredRelationship(String preferredRelationship);

    List<Profile> findProfilesByAgeRange(Integer minAge, Integer maxAge);

    List<Profile> findProfilesByGender(String gender);
}
