package app.service;

import app.model.Angajat;
import app.model.Taxi;

import java.util.List;

public interface TaxiService {
    Taxi save(Taxi taxi);

    Taxi update(Taxi taxi);

    List<Taxi> findAll();

    Taxi findById(Integer id);

    boolean delete(Taxi taxi);
    Taxi findByName(String name);
}
