package expensetracker.userservice.service;

import expensetracker.userservice.dto.request.CreateUserRequestDTO;
import expensetracker.userservice.dto.response.UserProfileResponseDTO;
import expensetracker.userservice.entity.UserProfile;
import expensetracker.userservice.event.AuthUserRegisteredEvent;
import expensetracker.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProfileRepository userProfileRepository;

    public void createUserProfileFromEvent(AuthUserRegisteredEvent event) {
        UserProfile profile = new UserProfile();
        profile.setUserId(event.getUserId());
        profile.setUsername(event.getUsername());
        profile.setEmail(event.getEmail());
        profile.setFirstName(event.getFirstName());
        profile.setLastName(event.getLastName());

        userProfileRepository.save(profile);
    }

    public UserProfileResponseDTO createUserProfile(CreateUserRequestDTO requestDTO) {
        UserProfile profile = new UserProfile();
        profile.setUserId(UUID.randomUUID().toString());
        profile.setUsername(requestDTO.getUsername());
        profile.setEmail(requestDTO.getEmail());
        profile.setFirstName(requestDTO.getFirstName());
        profile.setLastName(requestDTO.getLastName());

        userProfileRepository.save(profile);

        return UserProfileResponseDTO
                .builder()
                .userId(profile.getUserId())
                .username(profile.getUsername())
                .email(profile.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .build();
    }

    public UserProfileResponseDTO getUserProfile(String userId) {
        UserProfile userProfile = userProfileRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for ID: " + userId));

        return UserProfileResponseDTO
                .builder()
                .userId(userProfile.getUserId())
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .createdAt(userProfile.getCreatedAt())
                .updatedAt(userProfile.getUpdatedAt())
                .build();
    }

    public List<UserProfileResponseDTO> getAllUserProfiles() {
        return userProfileRepository
                .findAll()
                .stream()
                .map(profile -> UserProfileResponseDTO
                        .builder()
                        .userId(profile.getUserId())
                        .username(profile.getUsername())
                        .email(profile.getEmail())
                        .firstName(profile.getFirstName())
                        .lastName(profile.getLastName())
                        .createdAt(profile.getCreatedAt())
                        .updatedAt(profile.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }
}
