package chispa.chispa.services;

import chispa.chispa.models.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    Profile findById(Long id);

    Profile findByUserId(Long userId);

    Profile save(Profile profile);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);

    List<Profile> findAll();
    //Map<String, Long> countProfilesByPreferredRelationship(); //Esto lo podriamos implementar si es factible
}
