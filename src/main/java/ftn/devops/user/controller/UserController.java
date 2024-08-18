package ftn.devops.user.controller;

import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.messages.UserUpdatedMessage;
import ftn.devops.user.messaging.notifier.IUserNotifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserNotifier userNotifier;

    @GetMapping("/test")
    void testUserMessaging(){
        var userCreatedMessage = UserCreatedMessage.builder()
            .averageGrade(8.2f)
            .email("nadja@mail.com")
            .firstName("Test")
            .lastName("TestLN")
            .username("test username")
            .id(1)
            .build();

        var userUpdatedMessage = UserUpdatedMessage.builder()
            .averageGrade(8.2f)
            .firstName("Test")
            .lastName("TestLN")
            .id(1)
            .build();

        userNotifier.fireUserCreatedNotification(userCreatedMessage);
        userNotifier.fireUserUpdatedNotification(userUpdatedMessage);
    }

}
