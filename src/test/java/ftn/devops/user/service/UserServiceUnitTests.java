package ftn.devops.user.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ftn.devops.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ftn.devops.user.dto.UserData;
import ftn.devops.user.entity.Role;
import ftn.devops.user.entity.RoleType;
import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.notifier.impl.UserNotifier;
import ftn.devops.user.repository.RoleRepository;
import ftn.devops.user.repository.UserRepository;
import ftn.devops.user.service.implementation.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserNotifier userNotifier;

    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    void test_When_Read_Invalid_Username_Should_Throw() {
        // Arrange
        var username = "username";
        var password = "password";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(null);
        // Act
        var userData = userService.read(username, password);
        // Assert
        assertNull(userData);
    }

    @Test
    void test_When_Read_Valid_User_Should_Return() {
        // Arrange
        var username = "username";
        var password = "password";
        var user = new UserData("123", "name", "lastName", username, password, "email", RoleType.GUEST, 0);
        var userEntity = new User(user);
        userEntity.setRole(new Role(RoleType.GUEST));
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(userEntity);
        // Act
        var result = userService.read(username, password);
        // Assert
        assert result != null;
    }

    @Test
    void test_When_Create_Valid_User_Should_Return() {
        // Arrange
        var username = "username";
        var password = "password";
        var user = new UserData("123", "name", "lastName", username, password, "email", RoleType.GUEST, 0);
        when(userRepository.findByUsername(username)).thenReturn(null);
        var userEntity = new User(user);
        userEntity.setRole(new Role(RoleType.GUEST));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(roleRepository.findByRoleType(any())).thenReturn(new Role(RoleType.GUEST));
        // Act
        var result = userService.create(user);
        // Assert
        verify(userRepository, times(1)).findByUsername(username);
        verify(userNotifier, times(1)).fireUserCreatedNotification(any(UserCreatedMessage.class));
        assertNotNull(result);
    }

    @Test
    void test_When_Create_Existing_Username_Should_Throw() {
        // Arrange
        var username = "username";
        var password = "password";
        var user = new UserData(null, "name", "lastName", username, password, "email", RoleType.GUEST, 0);
        var userEntity = user.toEntity();
        userEntity.setRole(new Role(RoleType.GUEST));
        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        // Act
        var result = userService.create(user);
        // Assert
        assertNull(result);
    }


}
