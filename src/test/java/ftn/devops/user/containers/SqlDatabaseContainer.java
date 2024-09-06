package ftn.devops.user.containers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface SqlDatabaseContainer {
    @Container
    public static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:11");

    @BeforeAll
    static void beforeAll() {
        database.withInitScript("R__seed-data-test.sql");
        database.withInitScript("V1_0_0__create-tables-test.sql");
        database.start();
    }

    @AfterAll
    static void afterAll() {
        database.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.flyway.locations", () -> "filesystem:src/test/resources/db/migration");
    }
    
}
