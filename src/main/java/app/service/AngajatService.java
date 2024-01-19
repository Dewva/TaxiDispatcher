package app.service;

import app.model.Angajat;

import java.util.List;

public interface AngajatService {

    Angajat save(Angajat angajat);

    Angajat update(Angajat angajat);

    List<Angajat> findAll();

    Angajat findById(Integer id);

    boolean delete(Angajat angajat);
}
