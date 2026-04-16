package expensetracker.userservice.controller;

import expensetracker.userservice.dto.request.CreateUserRequestDTO;
import expensetracker.userservice.dto.response.UserProfileResponseDTO;
import expensetracker.userservice.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        UserProfileResponseDTO response = userService.createUserProfile(createUserRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable String userId ) {
        UserProfileResponseDTO response = userService.getUserProfile(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUserProfiles() {
        List<UserProfileResponseDTO> response = userService.getAllUserProfiles();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
