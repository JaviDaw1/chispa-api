package chispa.chispa.services;

import chispa.chispa.models.Users;

import java.util.List;

public interface UserService {
    Users findById(Long id);

    Users save(Users user);

    Users update(Long id, Users user);

    void deleteById(Long id);

    List<Users> findAll();

    Users findByEmail(String email);

    Long countActiveUsers();
}
