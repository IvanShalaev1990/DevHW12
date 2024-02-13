package org.spacetravel.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.spacetravel.entity.Client;
import org.spacetravel.entity.Ticket;
public class TicketDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Ticket getTicketById(long id) {
        session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }
    public int saveTicket(Ticket ticket) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(ticket);
        session.getTransaction().commit();
        session.close();
        return ticket.getId();
    }

    public int deleteTicketById(long id) {
        session = sessionFactory.openSession();
        Ticket ticket = null;
        transaction = null;
        try {
            transaction = session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
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
        return ticket.getId();
    }
    public Ticket uppDateTicket(Ticket ticket) {
        session = sessionFactory.openSession();
        Ticket existingTicket = null;
        transaction = null;
        try {
            transaction = session.beginTransaction();
            existingTicket = session.get(Ticket.class, ticket.getId());
            if (existingTicket != null) {
                existingTicket.setId(ticket.getId());
                existingTicket.setClientId(ticket.getClientId());
                existingTicket.setCreatedAt(ticket.getCreatedAt());
                existingTicket.setToPlanet(ticket.getToPlanet());
                existingTicket.setFromPlanet(ticket.getFromPlanet());
                session.merge(existingTicket);
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
        return existingTicket;
    }
}
