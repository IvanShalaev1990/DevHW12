package org.spacetravel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.spacetravel.entity.Client;

public class ClientDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public ClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Client getClientById(long id) {
        session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public int saveClient(Client client) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(client);
        session.getTransaction().commit();
        session.close();
        return client.getId();
    }

    public int deleteClientById(long id) {
        session = sessionFactory.openSession();
        Client client = getClientById(id);
        transaction = null;
        try {
            transaction = session.beginTransaction();
            if (client != null) {
                session.remove(client);
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
        return client.getId();
    }

    public Client uppDateClient(Client client) {
        session = sessionFactory.openSession();
        Client existingClient = null;
        transaction = null;
        try {
            transaction = session.beginTransaction();
            existingClient = session.get(Client.class, client.getId());
            if (existingClient != null) {
                existingClient.setId(client.getId());
                existingClient.setName(client.getName());
                session.merge(existingClient);
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
        return existingClient;
    }
}
