package ftn.devops.user.entity;

import ftn.devops.user.dto.UserData;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String address;

    private Float averageGrade;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User(UserData userData){
        this.setId(Integer.parseInt(userData.getId()));
        this.firstName = userData.getName();
        this.lastName = userData.getLastName();
        this.username = userData.getUsername();
        this.password = userData.getPassword();
        this.email = userData.getEmail();
        this.averageGrade = userData.getAvgRate();
    }
}

