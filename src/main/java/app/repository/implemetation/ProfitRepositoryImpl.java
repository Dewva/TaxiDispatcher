package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Dispatcher;
import app.model.Profit;
import app.model.SoferActivi;
import app.repository.ProfitRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class ProfitRepositoryImpl implements ProfitRepository {
    @Override
    public Profit save(Profit entity) {
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
    public Profit update(Profit entity) {
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
    public Profit findById(Integer id) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Profit> query = session.getNamedQuery("findProfitById");
        query.setParameter("id", id);
        Profit a;
        try {
            a = (Profit) query.getSingleResult();
        } catch (NoResultException e) {
            a = null;
        }

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return a;
    }

    @Override
    public List<Profit> findAll() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Profit> query = session.getNamedQuery("findAllProfit");
        List<Profit> entities = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return entities;
    }

    @Override
    public boolean delete(Profit entity) {
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
    public Profit findProfitByDate(Date givenDate) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Profit> query = session.getNamedQuery("findProfitByDate");
        query.setParameter("date", givenDate);
        Profit entity;
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
