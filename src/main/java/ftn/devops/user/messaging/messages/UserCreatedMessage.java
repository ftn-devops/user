package ftn.devops.user.messaging.messages;

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
}
