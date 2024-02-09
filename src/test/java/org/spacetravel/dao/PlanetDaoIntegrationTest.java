package org.spacetravel.dao;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.spacetravel.entity.Client;
import org.spacetravel.entity.Planet;
import org.spacetravel.enums.Planets;
import org.spacetravel.migration.FlywayMigration;
import org.spacetravel.util.HibernateTestUtil;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanetDaoIntegrationTest {
    private static PlanetDao planetDao;


    @BeforeAll
    static void setUp() {
        planetDao = new PlanetDao(HibernateTestUtil.getInstance().getSessionFactory());
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
    void testThatGetPlanetByIdWorksCorrectly() {
        Assertions.assertEquals(
                planetDao.getPlanetById(Planets.EARTH.toString()),
                new Planet(Planets.EARTH));
    }

    @Order(2)
    @Test
    void testThatSavePlanetWorksCorrectly() {
        Assertions.assertEquals(
                planetDao.savePlanet(new Planet(Planets.MOON)),
                Planets.MOON.getPlanetName());
    }

    @Order(3)
    @Test
    void testThatDeletePlanetByIdWorksCorrectly() {
        Assertions.assertEquals(
                planetDao.deletePlanetById(Planets.MOON.toString()),
                Planets.MOON.getPlanetName());
    }
    @Order(4)
    @Test
    void testThatUppDatePlanetWorksCorrectly() {
        Planet planetNotMoon = new Planet(Planets.EARTH);
        planetNotMoon.setName("NotMoon");
        Assertions.assertEquals(planetDao.uppDatePlanet(Planets.EARTH.toString(), "NotMoon"), planetNotMoon);
    }


}