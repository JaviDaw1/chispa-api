package chispa.chispa.services;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;

import java.util.List;

public interface ProfilesService {
    Profile findById(Long id);

    Profile findByUserId(Long userId);

    Profile save(Profile profile);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);

    List<Profile> findAll();

    List<Profile> findProfilesByGender(Gender gender);

    Profile setOnline(Profile profile);

    Profile setOffline(Profile profile);
}
