/*
package chispa.chispa.services;

import chispa.chispa.models.Users;
import chispa.chispa.models.enums.UserState;
import chispa.chispa.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public Users save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public Users update(Long id, Users user) {
        Users updatedUser = this.findById(id);
        updatedUser.setEmail(user.getEmail());

        if (!updatedUser.getPassword().equals(user.getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        updatedUser.setJoinDate(user.getJoinDate());
        updatedUser.setAge(user.getAge());
        updatedUser.setState(user.getState());
        return usersRepository.save(updatedUser);
    }

    @Override
    public Users patch(Long id, Users user) {
        Users userToPatch = usersRepository.findById(id).orElseThrow();
        if (user.getEmail() != null) {
            userToPatch.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !userToPatch.getPassword().equals(user.getPassword())) {
            userToPatch.setPassword(passwordEncoder.encode(user.getPassword()));  // Codificamos la nueva contraseña
        }
        if (user.getRole() != null) {
            userToPatch.setRole(user.getRole());
        }
        if (user.getState() != null) {
            userToPatch.setState(user.getState());
        }
        if (user.getAge() != null) {
            userToPatch.setAge(user.getAge());
        }
        return usersRepository.save(userToPatch);
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Long countAllUsers() {
        return usersRepository.count();
    }

    @Override
    public Long countUsersByState(UserState state) {
        return usersRepository.countUsersByState(state);
    }

    @Override
    public List<Users> findUsersByState(UserState state) {
        return usersRepository.findByState(state);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) usersRepository.findByEmail(email);
    }
}
*/
