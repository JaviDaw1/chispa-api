package chispa.chispa.mappers;

import chispa.chispa.dtos.UserRequestDTO;
import chispa.chispa.dtos.UserResponseDTO;
import chispa.chispa.models.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UsersMapper {
    public UserResponseDTO toResponse(Users user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getState(),
                user.getAge(),
                user.getJoinDate()
        );
    }
    public List<UserResponseDTO> toResponse(List<Users> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
    public Users toModel(UserRequestDTO userRequestDto) {
        return new Users(
                null,
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getRole(),
                userRequestDto.getState(),
                userRequestDto.getAge(),
                LocalDate.now()
        );
    }
}
