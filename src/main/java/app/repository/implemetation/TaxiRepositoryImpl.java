package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Angajat;
import app.model.Taxi;
import app.repository.TaxiRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TaxiRepositoryImpl implements TaxiRepository {
    @Override
    public Taxi save(Taxi entity) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        Integer idSavedEntity = (Integer) session.save(entity);

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return findById(idSavedEntity);
    }

    @Override
    public Taxi update(Taxi entity) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        Integer id = entity.getId();
        session.saveOrUpdate(entity);

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return findById(id);
    }

    @Override
    public Taxi findById(Integer id) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Taxi> query = session.getNamedQuery("findTaxiById");
        query.setParameter("id", id);
        Taxi a;
        try {
            a = (Taxi) query.getSingleResult();
        } catch (NoResultException e) {
            a = null;
        }

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return a;
    }

    @Override
    public List<Taxi> findAll() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Taxi> query = session.getNamedQuery("findAllTaxi");
        List<Taxi> entities = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entities;
    }

    @Override
    public boolean delete(Taxi entity) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        Integer id = entity.getId();
        session.delete(entity);

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return findById(id) == null;
    }

    @Override
    public Taxi findByNrInmatriculare(String nrInmatriculare) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Taxi> query = session.getNamedQuery("findTaxiByName");
        query.setParameter("nrInmatriculare", nrInmatriculare);
        Taxi entity;
        try {
            entity = query.getSingleResult();
        } catch (NoResultException e) {
            entity = null;
        }


        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entity;
    }
}
