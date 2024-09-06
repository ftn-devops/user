package ftn.devops.user.messaging.messages;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserRatedMessage extends BaseMessage {

    String recipientEmail;

    Float rating;
}
