package chispa.chispa.services;

import chispa.chispa.models.Profile;
import chispa.chispa.models.enums.Gender;
import chispa.chispa.repositories.ProfilesRepository;
import chispa.chispa.repositories.UsersDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the ProfilesService interface.
 * Handles business logic for user profiles.
 */
@Transactional
@Service
@AllArgsConstructor
public class ProfilesServiceImpl implements ProfilesService {
    // Repository dependencies
    private final ProfilesRepository profileRepository;
    private final UsersDetailsRepository usersRepository;

    @Override
    public Profile findById(Long id) {
        // Find profile or throw exception if not found
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profiles not found"));
    }

    @Override
    public Profile findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    @Override
    public Profile save(Profile profile) {
        // Validate user exists
        profile.setUser(usersRepository.findById(profile.getUser().getId()).orElseThrow());
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Long id, Profile profile) {
        Profile profileUpdated = this.findById(id);

        profileUpdated.setName(profile.getName());
        profileUpdated.setBio(profile.getBio());
        profileUpdated.setProfilePhoto(profile.getProfilePhoto());
        profileUpdated.setInterests(profile.getInterests());
        profileUpdated.setGender(profile.getGender());
        profileUpdated.setIsOnline(profile.getIsOnline());
        profileUpdated.setLastActive(LocalDateTime.now());
        profileUpdated.setLastName(profile.getLastName());
        profileUpdated.setPreferredRelationship(profile.getPreferredRelationship());
        profileUpdated.setLocation(profile.getLocation());

        if (profile.getLocation() != null) {
            var user = profileUpdated.getUser();
            if (user != null) {
                user.setLocation(profile.getLocation());
                usersRepository.save(user);
            }
        }

        return profileRepository.save(profileUpdated);
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public List<Profile> findProfilesByGender(Gender gender) {
        return profileRepository.findProfileByGender(gender);
    }

    @Override
    public Profile setOnline(Profile profile) {
        // Set profile online status to true
        profile.setIsOnline(true);
        return profileRepository.save(profile);
    }

    @Override
    public Profile setOffline(Profile profile) {
        // Set profile online status to false
        profile.setIsOnline(false);
        return profileRepository.save(profile);
    }
}