package org.spacetravel.dao;

import org.junit.jupiter.api.*;
import org.spacetravel.entity.Client;
import org.spacetravel.entity.Planet;
import org.spacetravel.entity.Ticket;
import org.spacetravel.enums.Planets;
import org.spacetravel.migration.FlywayMigration;
import org.spacetravel.util.HibernateTestUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TicketDaoIntegrationTest {
    private static TicketDao ticketDao;
    private static ClientDao clientDao;


    @BeforeAll
    static void setUp() {
        new FlywayMigration(HibernateTestUtil.postgres.getJdbcUrl(),
                HibernateTestUtil.postgres.getUsername(),
                HibernateTestUtil.postgres.getPassword());
        ticketDao = new TicketDao(HibernateTestUtil.getInstance().getSessionFactory());
        clientDao = new ClientDao(HibernateTestUtil.getInstance().getSessionFactory());

    }

    @AfterAll
    static void tearDown() {
        HibernateTestUtil.postgres.stop();
    }

    @Order(1)
    @Test
    void testThatGetTicketByIdWorksCorrectly() {
        Assertions.assertEquals(ticketDao.getTicketById(1), Ticket.builder().id(1).build());
    }
    @Order(2)
    @Test
    void testThatSaveTicketWorksCorrectly() {
        Ticket ticket = Ticket.builder()
                .clientId(new Client(1, "Rick Sanchez"))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .fromPlanet(new Planet(Planets.MARS))
                .toPlanet(new Planet(Planets.URANUS))
                .build();
        Assertions.assertEquals(ticketDao.saveTicket(ticket), 11);
    }
    @Order(3)
    @Test
    void testThatDeleteTicketByIdWorksCorrectly() {
        Assertions.assertEquals(ticketDao.deleteTicketById(11), 11);
    }
    @Order(4)
    @Test
    void testThatDeleteTicketByIdDoesNotDeleteClient() {
        Assertions.assertEquals(clientDao.getClientById(1), new Client(1, "Rick Sanchez"));
    }
    @Order(5)
    @Test
    void testThatUppDateTicketWorksCorrectly() {
        Ticket ticket = Ticket.builder()
                .clientId(new Client(1, "Rick Sanchez"))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .fromPlanet(new Planet(Planets.MARS))
                .toPlanet(new Planet(Planets.URANUS))
                .build();
        Ticket ticketToUppDate = Ticket.builder()
                .id(12)
                .clientId(new Client(1, "Rick Sanchez"))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .fromPlanet(new Planet(Planets.URANUS))
                .toPlanet(new Planet(Planets.MOON))
                .build();
        ticketDao.saveTicket(ticket);
        Assertions.assertEquals(ticketDao.uppDateTicket(ticketToUppDate), ticketToUppDate);
    }
}