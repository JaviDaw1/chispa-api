package chispa.chispa.mappers;

import chispa.chispa.dtos.UsersRequestDTO;
import chispa.chispa.dtos.UsersResponseDTO;
import chispa.chispa.models.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UsersMapper {
    public UsersResponseDTO toResponse(Users user) {
        return new UsersResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getState(),
                user.getAge(),
                user.getJoinDate()
        );
    }
    public List<UsersResponseDTO> toResponse(List<Users> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
    public Users toModel(UsersRequestDTO userRequestDto) {
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
