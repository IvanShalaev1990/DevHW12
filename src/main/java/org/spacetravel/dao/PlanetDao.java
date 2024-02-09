package org.spacetravel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.spacetravel.entity.Planet;
import org.spacetravel.enums.Planets;

public class PlanetDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public PlanetDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Planet getPlanetById(String id) {
        session = sessionFactory.openSession();
        Planet planet = session.get(Planet.class, Planets.valueOf(id));
        session.close();
        return planet;
    }

    public String savePlanet(Planet planet) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(planet);
        session.getTransaction().commit();
        session.close();
        return planet.getName();
    }

    public String deletePlanetById(String id) {
        session = sessionFactory.openSession();
        Planet planet = getPlanetById(id);
        transaction = null;
        try {
            transaction = session.beginTransaction();
            if (planet != null) {
                session.remove(planet);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return planet.getName();
    }

    public Planet uppDatePlanet(String id, String name) {
        session = sessionFactory.openSession();
        Planet existingPlanet = null;
        transaction = null;
        try {
            transaction = session.beginTransaction();
            existingPlanet = session.get(Planet.class, Planets.valueOf(id));
            if (existingPlanet != null) {
                existingPlanet.setName(name);
                session.merge(existingPlanet);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return existingPlanet;
    }
}
