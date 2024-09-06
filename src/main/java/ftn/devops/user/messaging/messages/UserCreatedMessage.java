package ftn.devops.user.messaging.messages;

import ftn.devops.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserCreatedMessage extends BaseMessage{

    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String address;

    private Float averageGrade;

    public UserCreatedMessage(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.averageGrade = user.getAverageGrade();
    }

    public UserCreatedMessage(Integer id, String firstName, String lastName, String username, String email, String address, Float averageGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.averageGrade = averageGrade;
    }
}
