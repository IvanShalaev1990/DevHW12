package org.spacetravel.util;


import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.spacetravel.entity.Client;
import org.spacetravel.entity.Planet;
import org.spacetravel.entity.Ticket;
import org.testcontainers.containers.PostgreSQLContainer;

public class HibernateTestUtil {
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpassword");
    private static final HibernateTestUtil INSTANCE;

@Getter
    private SessionFactory sessionFactory;

    static {
        postgres.start();
        INSTANCE = new HibernateTestUtil();
    }

    private HibernateTestUtil() {

        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy())
                .setProperty("hibernate.connection.user", postgres
                        .getUsername())
                .setProperty("hibernate.connection.password", postgres
                        .getPassword())
                .setProperty("hibernate.connection.url", postgres
                        .getJdbcUrl())
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.pool_size", "10")
                .setProperty("hibernate.connection.autocommit", "true")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .buildSessionFactory();
    }

    public static HibernateTestUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

}
