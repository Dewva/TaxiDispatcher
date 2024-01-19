package app.single_point_access;

import app.service.*;
import app.service.implementation.*;
import app.service.performance.AppPerformanceService;
import app.service.performance.SQLProcedurePerformanceService;

import java.security.PrivateKey;

public class ServiceSinglePointAccess {

    private static AngajatService angajatService;
    private static TaxiService taxiService;
    private static ProfitService profitService;
    private static DispatcherService dispatcherService;
    private static SoferActiviService soferActiviService;
    private static AppPerformanceService appPerformanceService;
    private static SQLProcedurePerformanceService SQLProcedurePerformanceService;

    static {

        appPerformanceService = new AppPerformanceService();
        SQLProcedurePerformanceService = new SQLProcedurePerformanceService();
        angajatService = new AngajatServiceImpl();
        taxiService = new TaxiServiceImpl();
        profitService = new ProfitServiceImpl();
        dispatcherService = new DispatcherServiceImpl();
        soferActiviService = new SoferActiviServiceImpl();
    }

    public static AppPerformanceService getAppPerformanceService() {
        return appPerformanceService;
    }

    public static app.service.performance.SQLProcedurePerformanceService getSQLProcedurePerformanceService() {
        return SQLProcedurePerformanceService;
    }


    public static AngajatService getAngajatService() {
        return angajatService;
    }

    public static TaxiService getTaxiService() {
        return taxiService;
    }

    public static ProfitService getProfitService() {
        return profitService;
    }

    public static DispatcherService getDispatcherService() {
        return dispatcherService;
    }

    public static SoferActiviService getSoferActiviService() {
        return soferActiviService;
    }
}
