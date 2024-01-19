package app.repository;

import app.model.Dispatcher;

public interface DispatcherRepository extends CRUDRepository<Dispatcher, Integer>{
    Dispatcher findByName(String name);
}
