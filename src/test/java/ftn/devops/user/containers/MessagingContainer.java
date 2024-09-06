package ftn.devops.user.containers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface MessagingContainer {
	@Container
	public static RabbitMQContainer messaging = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");

	@BeforeAll
	static void beforeAll() {
		messaging.start();
	}

	@AfterAll
	static void afterAll() {
		messaging.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.rabbitmq.addresses", messaging::getHost);
		registry.add("spring.rabbitmq.port", messaging::getAmqpPort);
		registry.add("spring.rabbitmq.username", () -> "devops");
		registry.add("spring.rabbitmq.password", () -> "devops");
	}
}
