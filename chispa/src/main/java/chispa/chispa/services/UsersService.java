package chispa.chispa.services;

import chispa.chispa.models.Users;

import java.util.List;

public interface UsersService {
    Users findById(Long id);

    Users save(Users user);

    Users update(Long id, Users user);

    Users patch(Long id, Users user);

    void deleteById(Long id);

    List<Users> findAll();

    //List<Users> findUsersByIncompleteProfiles(List<Profile> profiles);

    Users findByEmail(String email);

    Long countActiveUsers();

    Long countAllUsers();

    List<Users> findUsersByState(String state);
}
