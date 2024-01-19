package app.repository;

import app.model.Taxi;

public interface TaxiRepository extends CRUDRepository<Taxi,Integer>{
    Taxi findByNrInmatriculare(String nrInmatriculare);
}
