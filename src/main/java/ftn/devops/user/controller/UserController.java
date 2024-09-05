package ftn.devops.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.devops.user.dto.UserData;
import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.messages.UserUpdatedMessage;
import ftn.devops.user.messaging.notifier.IUserNotifier;
import ftn.devops.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserNotifier userNotifier;

    private final UserService userService;

    @GetMapping("/login")
    UserData login(String username, String password) {
        return userService.read(username, password);
    }

    @PostMapping("/register")
    UserData register(@RequestBody UserData userData) {
        return userService.create(userData);
    }

    @PutMapping("/update")
    UserData update(String username, String password, @RequestBody UserData userData) {
        return userService.update(username, password, userData);
    }

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
