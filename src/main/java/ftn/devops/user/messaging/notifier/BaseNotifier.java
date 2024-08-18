package ftn.devops.user.messaging.notifier;

import ftn.devops.user.messaging.messages.BaseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseNotifier {

    private final RabbitTemplate rabbitTemplate;

    protected <T extends BaseMessage> void sendMessage(String exchange, String routingKey, T message) {
        log.info("Sending {}: {}...", message.getClass(), message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Sent.");
    }

    protected <T extends BaseMessage> void sendMessages(String exchange, String routingKey, List<T> messages) {
        log.info("Sending {}: {}...", messages.getClass(), messages);
        rabbitTemplate.convertAndSend(exchange, routingKey, messages);
        log.info("Sent.");
    }
}
