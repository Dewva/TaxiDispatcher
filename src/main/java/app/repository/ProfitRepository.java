package app.repository;

import app.model.Profit;

import java.util.Date;

public interface ProfitRepository extends CRUDRepository<Profit, Integer>{
    Profit findProfitByDate(Date givenDate);
}
