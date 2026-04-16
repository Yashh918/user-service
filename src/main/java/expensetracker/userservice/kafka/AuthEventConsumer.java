package expensetracker.userservice.kafka;

import expensetracker.userservice.event.AuthUserRegisteredEvent;
import expensetracker.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthEventConsumer {

    private final UserService userService;

    @KafkaListener(topics = KafkaTopics.USER_REGISTER)
    public void consume(AuthUserRegisteredEvent event) {
        userService.createUserProfileFromEvent(event);
    }

}
