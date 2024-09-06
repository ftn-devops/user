package ftn.devops.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

import ftn.devops.user.containers.MessagingConfig;
import ftn.devops.user.containers.MessagingContainer;
import ftn.devops.user.containers.SqlDatabaseContainer;
import ftn.devops.user.dto.UserData;
import ftn.devops.user.entity.RoleType;
import ftn.devops.user.messaging.messages.UserCreatedMessage;
import ftn.devops.user.repository.UserRepository;
import ftn.devops.user.service.implementation.UserServiceImpl;
import ftn.devops.user.util.constants.UserMessagingConstants;

@SpringBootTest
@Import({MessagingConfig.class})
class UserServiceIntegrationTests implements SqlDatabaseContainer, MessagingContainer {
    @Autowired
    public UserServiceImpl userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private ObjectMapper objectMapper;

    private Queue queue;

    @Test
    void test_When_Read_Invalid_Username_Should_Throw() {
        // Arrange
        var username = "username";
        var password = "password";
        
        // Act
        var user = userService.read(username, password);
        // Assert
        assertNull(user);
    }

    @Test
    void test_When_Read_Valid_User_Should_Return() {
        // Arrange
        var username = "john.doe";
        var password = "password";
        
        // Act
        var result = userService.read(username, password);
        // Assert
        assertNotNull(result);
    }

    @Test
    void test_When_Create_Valid_User_Should_Return() {
        // Arrange
        var username = "brka";
        var password = "password";

        var exchange = new TopicExchange(UserMessagingConstants.Exchange.USER);
        amqpAdmin.declareExchange(exchange);
        queue = amqpAdmin.declareQueue();
        amqpAdmin.declareBinding(BindingBuilder.bind(queue)
                                               .to(exchange)
                                               .with(UserMessagingConstants.RoutingKey.USER_CREATE)
                                               );
        
        // Act
        var result = userService.create(UserData.builder().username(username).password(password).role(RoleType.GUEST).build());

        // Assert
        assertNotNull(result);
        Message message = rabbitTemplate.receive(queue.getName());
        UserCreatedMessage userCreatedMessage = null;
        try {
            userCreatedMessage = objectMapper.readValue(message.getBody(), UserCreatedMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(userCreatedMessage);
        assertEquals(userCreatedMessage.getUsername(), username);
    }
}
