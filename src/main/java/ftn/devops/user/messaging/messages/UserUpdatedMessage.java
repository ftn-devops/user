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
public class UserUpdatedMessage extends BaseMessage {

    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private Float averageGrade;

    public UserUpdatedMessage(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.averageGrade = user.getAverageGrade();
    }

    public UserUpdatedMessage(Integer id, String firstName, String lastName, String address, Float averageGrade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.averageGrade = averageGrade;
    }
}
