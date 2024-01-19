package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Angajat;
import app.model.SoferActivi;
import app.model.Taxi;
import app.repository.SoferActiviRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class SoferActiviRepositoryImpl implements SoferActiviRepository {
    @Override
    public SoferActivi save(SoferActivi entity) {
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
    public SoferActivi update(SoferActivi entity) {
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
    public SoferActivi findById(Integer id) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<SoferActivi> query = session.getNamedQuery("findSoferActiviById");
        query.setParameter("id", id);
        SoferActivi a;
        try {
            a = (SoferActivi) query.getSingleResult();
        } catch (NoResultException e) {
            a = null;
        }

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return a;
    }

    @Override
    public List<SoferActivi> findAll() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<SoferActivi> query = session.getNamedQuery("findAllSoferActivi");
        List<SoferActivi> entities = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entities;
    }

    @Override
    public boolean delete(SoferActivi entity) {
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
    public SoferActivi findSoferActiviByName(String name) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<SoferActivi> query = session.getNamedQuery("findSoferActiviByName");
        query.setParameter("name", name);
        SoferActivi entity;
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

    @Override
    public List<SoferActivi> findFreeSoferActivi() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<SoferActivi> query = session.getNamedQuery("findFreeSoferActivi");
        List<SoferActivi> entities = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entities;
    }

    @Override
    public Integer findCurrentProfit() {
        List<SoferActivi> entities = findAll();
        Integer sum = 0;
        for (SoferActivi s:
             entities) {
            sum += s.getProfit();
        }

        return sum;
    }

}
