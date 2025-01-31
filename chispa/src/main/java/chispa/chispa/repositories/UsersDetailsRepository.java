package chispa.chispa.repositories;

import chispa.chispa.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDetailsRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);
}