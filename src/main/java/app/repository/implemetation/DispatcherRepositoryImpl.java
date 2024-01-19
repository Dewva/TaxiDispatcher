package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Dispatcher;
import app.model.SoferActivi;
import app.repository.DispatcherRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class DispatcherRepositoryImpl implements DispatcherRepository {
    @Override
    public Dispatcher save(Dispatcher entity) {
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
    public Dispatcher update(Dispatcher entity) {
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
    public Dispatcher findById(Integer id) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Dispatcher> query = session.getNamedQuery("findDispatcherById");
        query.setParameter("id", id);
        Dispatcher a;
        try {
            a = (Dispatcher) query.getSingleResult();
        } catch (NoResultException e) {
            a = null;
        }

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return a;
    }

    @Override
    public List<Dispatcher> findAll() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Dispatcher> query = session.getNamedQuery("findAllDispatcher");
        List<Dispatcher> entities = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entities;
    }

    @Override
    public boolean delete(Dispatcher entity) {
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
    public Dispatcher findByName(String name) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Dispatcher> query = session.getNamedQuery("findDispatcherByName");
        query.setParameter("name", name);
        Dispatcher entity;
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
