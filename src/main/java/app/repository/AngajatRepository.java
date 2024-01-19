package app.repository;

import app.model.Angajat;

import java.util.List;

public interface AngajatRepository extends CRUDRepository<Angajat, Integer> {
    Angajat findByName(String name);
    Angajat findByTaxi(String nrInmatriculare);
    List<Angajat> findAllByTimeOfWork (String timeOfWork);
}
