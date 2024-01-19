package app.single_point_access;

import app.model.Profit;
import app.repository.*;
import app.repository.implemetation.*;

public class RepositorySinglePointAccess {


    private static AngajatRepository angajatRepository;
    private static TaxiRepository taxiRepository;
    private static DispatcherRepository dispatcherRepository;
    private static ProfitRepository profitRepository;
    private static SoferActiviRepository soferActiviRepository;

    static {


        angajatRepository = new AngajatRepositoryImpl();
        taxiRepository = new TaxiRepositoryImpl();
        dispatcherRepository = new DispatcherRepositoryImpl();
        profitRepository = new ProfitRepositoryImpl();
        soferActiviRepository = new SoferActiviRepositoryImpl();
    }


    public static AngajatRepository getAngajatRepository(){return angajatRepository;}

    public static ProfitRepository getProfitRepository() {
        return profitRepository;
    }

    public static SoferActiviRepository getSoferActiviRepository() {
        return soferActiviRepository;
    }

    public static TaxiRepository getTaxiRepository(){return taxiRepository;}
    public static DispatcherRepository getDispatcherRepository(){return dispatcherRepository;}
}
