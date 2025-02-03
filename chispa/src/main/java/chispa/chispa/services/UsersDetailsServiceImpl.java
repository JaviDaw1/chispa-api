package chispa.chispa.services;

import chispa.chispa.auth.SignupRequest;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.Role;
import chispa.chispa.repositories.UsersDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {
    private final UsersDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public Users save(Users user) {
        return userDetailsRepository.save(user);
    }

    public void deleteById(Long id) {
        userDetailsRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public List<Users> getAll() {
        return userDetailsRepository.findAll();
    }

    //    public UsersDetails create(SignupRequest signupRequest){
//        return userDetailsRepository.save(
//                new Users(
//                        signupRequest.getEmail(),
//                        passwordEncoder.encode(signupRequest.getPassword())
//                )
//        );
//    }
    public UserDetails create(SignupRequest signupRequest) {
        Users user = new Users(
                signupRequest.getFirstname(),
                signupRequest.getLastname(),
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );
        Role role = signupRequest.getUserRole() != null ? signupRequest.getUserRole() : Role.valueOf("USER");
        user.setRole(role);
        return userDetailsRepository.save(user);
    }

    public Users findById(Long userId) {
        return userDetailsRepository.findById(userId).orElse(null);
    }
}