package org.spacetravel.dao;

import org.junit.jupiter.api.*;
import org.spacetravel.entity.Client;
import org.spacetravel.migration.FlywayMigration;
import org.spacetravel.util.HibernateTestUtil;

import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientDaoIntegrationTest {
    private static ClientDao clientDao;


    @BeforeAll
    static void setUp() {
        clientDao = new ClientDao(HibernateTestUtil.getInstance().getSessionFactory());
        new FlywayMigration(HibernateTestUtil.postgres.getJdbcUrl(),
                HibernateTestUtil.postgres.getUsername(),
                HibernateTestUtil.postgres.getPassword());

    }
    @AfterAll
    static void tearDown() {
        HibernateTestUtil.postgres.stop();
    }

    @Order(1)
    @Test
    void testThatGetClientByIdWorksCorrectly() {
        Assertions.assertEquals(clientDao.getClientById(1), new Client(1, "Rick Sanchez"));
    }
    @Order(2)
    @Test
    void testThatSaveClientWorksCorrectly() {
        Client client = new Client("Mr Presedent");
        Assertions.assertEquals(clientDao.saveClient(client), 11);
    }
    @Order(3)
    @Test
    void testThatDeleteClientByIdWorksCorrectly() {
        Client client = new Client("Mr Presedent");
        clientDao.saveClient(client);
        Assertions.assertEquals(clientDao.deleteClientById(11), 11);
    }
    @Order(4)
    @Test
    void testThatUppDateClientWorksCorrectly() {
        Client client = new Client("Mr Presedent");
        clientDao.saveClient(client);
        Assertions.assertEquals(clientDao.uppDateClient(new Client(11, "John Doe")), new Client(11, "John Doe"));
    }
}