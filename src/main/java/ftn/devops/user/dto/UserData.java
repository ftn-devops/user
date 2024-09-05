package ftn.devops.user.dto;

import ftn.devops.user.entity.RoleType;
import ftn.devops.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private Integer id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private RoleType role;
    private float avgRate;

    public UserData(User user) {
        this.id = user.getId();
        this.name = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole().getRoleType();
        this.avgRate = user.getAverageGrade();
    }

    public UserData(Integer id, String name, String lastName, String username, String password, String email, RoleType role, float avgRate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avgRate = avgRate;
    }

    public User toEntity() {
        return User.builder()
            .firstName(this.name)
            .lastName(this.lastName)
            .username(this.username)
            .password(this.password)
            .email(this.email)
            .averageGrade(this.avgRate)
            .build();
    }
}
