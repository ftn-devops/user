package ftn.devops.user.containers;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MessagingConfig implements MessagingContainer {
	@Bean
	@Primary
    public Jackson2JsonMessageConverter testConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
	@Primary
    public CachingConnectionFactory testConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(messaging.getHost(), messaging.getMappedPort(5672));
        connectionFactory.setUsername(messaging.getAdminUsername());
        connectionFactory.setPassword(messaging.getAdminPassword());
		return connectionFactory;
	}

	@Bean
	@Primary
	public AmqpAdmin testAmqpAdmin(CachingConnectionFactory connectionFactory) {
		var rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.getBeanName();
		return rabbitAdmin;
	}

    @Bean
	@Primary
	public RabbitTemplate testRabbitTemplate(CachingConnectionFactory connectionFactory) {
		var template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(testConverter());
		return template;
	}

}
