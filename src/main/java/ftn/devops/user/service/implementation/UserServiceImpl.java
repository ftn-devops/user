package ftn.devops.user.service.implementation;

import org.springframework.stereotype.Service;

import ftn.devops.user.dto.UserData;
import ftn.devops.user.entity.Role;
import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.messages.UserUpdatedMessage;
import ftn.devops.user.messaging.notifier.IUserNotifier;
import ftn.devops.user.repository.RoleRepository;
import ftn.devops.user.repository.UserRepository;
import ftn.devops.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final IUserNotifier userNotifier;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserData read(String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        }
        return new UserData(user);
    }

    @Override
    public UserData create(UserData user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        var newUser = user.toEntity();
        newUser.setRole(getRole(user));
        var userEntity = userRepository.save(newUser);
        userNotifier.fireUserCreatedNotification(new UserCreatedMessage(userEntity));
        return new UserData(userEntity);
    }

    @Override
    public UserData update(String username, String password, UserData user) {
        var userEntity = userRepository.findByUsernameAndPassword(username, password);
        if (userEntity == null) {
            return null;
        }
        var newUser = user.toEntity();
        newUser.setRole(getRole(user));
        var updatedUser = userRepository.save(newUser);
        userNotifier.fireUserUpdatedNotification(new UserUpdatedMessage(updatedUser));
        return new UserData(updatedUser);
    }

    private Role getRole(UserData user) {
        return roleRepository.findByRoleType(user.getRole());
    }
}
