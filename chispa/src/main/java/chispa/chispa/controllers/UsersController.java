package chispa.chispa.controllers;

import chispa.chispa.dtos.UsersRequestDTO;
import chispa.chispa.dtos.UsersResponseDTO;
import chispa.chispa.mappers.UsersMapper;
import chispa.chispa.models.Users;
import chispa.chispa.models.enums.UserState;
import chispa.chispa.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class UsersController {
    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @GetMapping("")
    public ResponseEntity<List<UsersResponseDTO>> getAllUsers() {
        log.info("getAllUsers");

        return ResponseEntity.ok(usersMapper.toResponse(usersService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserById(@PathVariable Long id) {
        log.info("getUserById");
        return ResponseEntity.ok(usersMapper.toResponse(usersService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> postUser(@RequestBody UsersRequestDTO usersRequestDto) {
        log.info("addUser");
        Users userSaved = usersService.save(usersMapper.toModel(usersRequestDto));
        return ResponseEntity.created(null).body(usersMapper.toResponse(userSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> putUser(@PathVariable Long id, @RequestBody UsersRequestDTO usersRequestDto) {
        log.info("putUser");
        Users userUpdated = usersService.update(id, usersMapper.toModel(usersRequestDto));
        return ResponseEntity.ok(usersMapper.toResponse(userUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("deleteUser");
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UsersResponseDTO> patchUser
            (@PathVariable Long id, @RequestBody UsersRequestDTO usersRequestDto) {

        log.info("patchGeneralUser");

        Users userPatched = usersService.patch(id, usersMapper.toModel(usersRequestDto));

        return ResponseEntity.ok(usersMapper.toResponse(userPatched));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsersResponseDTO> getUserByEmail(@PathVariable String email) {
        log.info("getUserByEmail");
        return ResponseEntity.ok(usersMapper.toResponse(usersService.findByEmail(email)));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllUsers() {
        log.info("countAllUsers");
        return ResponseEntity.ok(usersService.countAllUsers());
    }

    @GetMapping("/count/state/{state}")
    public ResponseEntity<Long> countUsersByState(@PathVariable UserState state) {
        log.info("countUsersByState");
        return ResponseEntity.ok(usersService.countUsersByState(state));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<UsersResponseDTO>> getUsersByState(@PathVariable UserState state) {
        log.info("getUsersByState");
        return ResponseEntity.ok(usersMapper.toResponse(usersService.findUsersByState(state)));
    }
}

