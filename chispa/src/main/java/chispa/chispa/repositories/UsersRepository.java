package chispa.chispa.repositories;

import chispa.chispa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Long countUsersByState(String state);

    List<Users> findByState(String state);
}
