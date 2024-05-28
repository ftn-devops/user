package ftn.devops.user.entity;

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
}
