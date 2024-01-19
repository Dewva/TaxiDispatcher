package app.service;

import app.model.Profit;

import java.util.Date;
import java.util.List;

public interface ProfitService {
    Profit save(Profit profit);

    Profit update(Profit profit);

    List<Profit> findAll();

    Profit findById(Integer id);

    boolean delete(Profit profit);
    Profit findByDate(Date date);
}
