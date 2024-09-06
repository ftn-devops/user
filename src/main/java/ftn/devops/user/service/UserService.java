package ftn.devops.user.service;

import ftn.devops.user.dto.UserData;

public interface UserService {
    UserData read(String username, String password);
    UserData create(UserData user);
    UserData update(String username, String password, UserData user);
}