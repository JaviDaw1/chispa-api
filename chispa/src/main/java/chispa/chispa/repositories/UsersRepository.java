package chispa.chispa.repositories;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Long countUsersByState(UserState state);

    List<Users> findByState(UserState state);
}
