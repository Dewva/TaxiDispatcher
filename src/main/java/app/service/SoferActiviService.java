package app.service;

import app.model.SoferActivi;

import java.util.List;

public interface SoferActiviService {
    SoferActivi save(SoferActivi soferActivi);

    SoferActivi update(SoferActivi soferActivi);

    List<SoferActivi> findAll();

    SoferActivi findById(Integer id);

    boolean delete(SoferActivi soferActivi);
}
