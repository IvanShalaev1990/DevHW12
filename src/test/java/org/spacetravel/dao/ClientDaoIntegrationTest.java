package org.spacetravel.dao;

import org.junit.jupiter.api.*;
import org.spacetravel.entity.Client;
import org.spacetravel.entity.Ticket;
import org.spacetravel.migration.FlywayMigration;
import org.spacetravel.util.HibernateTestUtil;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientDaoIntegrationTest {
    private static ClientDao clientDao;
    private static TicketDao ticketDao;


    @BeforeAll
    static void setUp() {
        new FlywayMigration(HibernateTestUtil.postgres.getJdbcUrl(),
                HibernateTestUtil.postgres.getUsername(),
                HibernateTestUtil.postgres.getPassword());
        clientDao = new ClientDao(HibernateTestUtil.getInstance().getSessionFactory());
        ticketDao = new TicketDao(HibernateTestUtil.getInstance().getSessionFactory());
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
    void testThatDeleteClientByIdDeletesTicketOfThisClient() {
        clientDao.deleteClientById(1);

        Assertions.assertEquals(ticketDao.getTicketById(1), null);
    }

    @Order(5)
    @Test
    void testThatUppDateClientWorksCorrectly() {
        Client client = new Client("Mr Presedent");
        clientDao.saveClient(client);
        Assertions.assertEquals(clientDao.uppDateClient(new Client(12, "John Doe")), new Client(12, "John Doe"));
    }

}