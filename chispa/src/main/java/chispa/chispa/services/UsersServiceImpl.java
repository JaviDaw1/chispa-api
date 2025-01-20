package chispa.chispa.services;

import chispa.chispa.models.Users;
import chispa.chispa.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Users not found"));
    }

    @Override
    public Users save(Users user) {
        user.setJoinDate(usersRepository.findById(user.getId()).get().getJoinDate());
        user.setPassword(usersRepository.findById(user.getId()).get().getPassword());
        user.setEmail(usersRepository.findById(user.getId()).get().getEmail());
        return usersRepository.save(user);
    }

    @Override
    public Users update(Long id, Users user) {
        Users updatedUser = this.findById(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
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
        if (user.getPassword() != null) {
            userToPatch.setPassword(user.getPassword());
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
    public Long countActiveUsers() {
        return usersRepository.countUsersByState("Active");
    }

    @Override
    public Long countAllUsers() {
        return usersRepository.count();
    }
}
