package app.repository.implemetation;

import app.configuration.HibernateConfiguration;
import app.model.Angajat;
import app.repository.AngajatRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AngajatRepositoryImpl implements AngajatRepository {
    @Override
    public Angajat findByName(String name) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Angajat> query = session.getNamedQuery("findAngajatByName");
        query.setParameter("name", name);
        Angajat angajat;
        try {
            angajat = query.getSingleResult();
        } catch (NoResultException e) {
            angajat = null;
        }


        // CLOSE SESSION
        transaction.commit();
        session.close();
        return angajat;
    }

    @Override
    public Angajat findByTaxi(String nrInmatriculare) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Angajat> query = session.getNamedQuery("findAngajatByTaxi");
        query.setParameter("nrInmatriculare", nrInmatriculare);
        Angajat angajat;
        try {
            angajat = query.getSingleResult();
        } catch (NoResultException e) {
            angajat = null;
        }


        // CLOSE SESSION
        transaction.commit();
        session.close();
        return angajat;
    }

    @Override
    public List<Angajat> findAllByTimeOfWork(String timeOfWork) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        timeOfWork = timeOfWork.toLowerCase();
        boolean timeParam;
        if (timeOfWork.equals("night"))
            timeParam = true;
        else
            timeParam = false;
        TypedQuery<Angajat> query = session.getNamedQuery("findAngajatByTimeOfWork");
        query.setParameter("timeOfWork", timeParam);
        List<Angajat> angajats = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return angajats;
    }

    @Override
    public Angajat save(Angajat entity) {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        Integer idAngajatSalvat = (Integer) session.save(entity);

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return findById(idAngajatSalvat);
    }

    @Override
    public Angajat update(Angajat entity) {
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
    public Angajat findById(Integer id) {
        // OPEN SESSION-FACTORY
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
       TypedQuery<Angajat> query = session.getNamedQuery("findAngajatById");
       query.setParameter("id", id);
        Angajat a;
        try {
            a = (Angajat) query.getSingleResult();
        } catch (NoResultException e) {
            a = null;
        }

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return a;
    }

    @Override
    public List<Angajat> findAll() {
        // OPEN SESSION
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // FUNCTION LOGIC
        TypedQuery<Angajat> query = session.getNamedQuery("findAllAngajat");
        List<Angajat> angajats = query.getResultList();

        // CLOSE SESSION
        transaction.commit();
        session.close();
        return angajats;
    }

    @Override
    public boolean delete(Angajat entity) {
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
}
