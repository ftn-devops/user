package ftn.devops.user.messaging.notifier;

import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.messages.UserUpdatedMessage;

public interface IUserNotifier {

    void fireUserCreatedNotification(UserCreatedMessage message);

    void fireUserUpdatedNotification(UserUpdatedMessage message);
}
