package expensetracker.userservice.service;

import expensetracker.userservice.entity.UserProfile;
import expensetracker.userservice.event.AuthUserRegisteredEvent;
import expensetracker.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public void createUserProfile(AuthUserRegisteredEvent event) {
        UserProfile profile = new UserProfile();
        profile.setUserId(event.getUserId());
        profile.setUsername(event.getUsername());
        profile.setEmail(event.getEmail());
        profile.setFirstName(event.getFirstName());
        profile.setLastName(event.getLastName());

        userProfileRepository.save(profile);
    }
}
