package app.service.performance;

import app.configuration.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.hibernate.query.procedure.ProcedureParameter;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class SQLProcedurePerformanceService implements PerformanceService {
    @Override
    @Transactional
    public void applyLogicOnUsers(Date date) {
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
        Session session = sessionFactory.openSession();
        ProcedureCall query = session.createStoredProcedureCall("apply_logic_on_users");
        ProcedureParameter<Date> inParam = query.registerParameter(1,Date.class, ParameterMode.IN);
        query.setParameter(inParam, date);
        query.execute();
    }
}
