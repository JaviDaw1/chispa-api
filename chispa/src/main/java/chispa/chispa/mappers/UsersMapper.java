package chispa.chispa.mappers;

import chispa.chispa.dtos.UsersResponseDTO;
import chispa.chispa.models.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersMapper {
    public UsersResponseDTO toResponse(Users user) {
        return new UsersResponseDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
    public List<UsersResponseDTO> toResponse(List<Users> user) {
        return user.stream()
                .map(this::toResponse)
                .toList();
    }
}
