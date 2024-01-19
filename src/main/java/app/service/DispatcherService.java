package app.service;

import app.model.Dispatcher;

import java.util.List;

public interface DispatcherService {
    Dispatcher save(Dispatcher dispatcher);

    Dispatcher update(Dispatcher dispatcher);

    List<Dispatcher> findAll();

    Dispatcher findById(Integer id);

    boolean delete(Dispatcher dispatcher);
}
