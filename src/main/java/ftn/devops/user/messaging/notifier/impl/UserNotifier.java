package ftn.devops.user.messaging.notifier.impl;

import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.messaging.messages.UserRatedMessage;
import ftn.devops.user.messaging.messages.UserUpdatedMessage;
import ftn.devops.user.messaging.notifier.BaseNotifier;
import ftn.devops.user.messaging.notifier.IUserNotifier;
import ftn.devops.user.util.constants.UserMessagingConstants.Exchange;
import ftn.devops.user.util.constants.UserMessagingConstants.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserNotifier extends BaseNotifier implements IUserNotifier {

    public UserNotifier(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    public void fireUserCreatedNotification(UserCreatedMessage message) {
        super.sendMessage(Exchange.USER, RoutingKey.USER_CREATE, message);
    }

    @Override
    public void fireUserUpdatedNotification(UserUpdatedMessage message) {
        super.sendMessage(Exchange.USER, RoutingKey.USER_UPDATE, message);
    }

    @Override
    public void fireUserRateddNotification(UserRatedMessage message) {
        super.sendMessage(Exchange.USER, RoutingKey.USER_RATED, message);
    }
}
