package app.service.implementation;

import app.model.Dispatcher;
import app.repository.DispatcherRepository;
import app.service.DispatcherService;
import app.single_point_access.RepositorySinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;

import java.util.List;

public class DispatcherServiceImpl implements DispatcherService {
    private DispatcherRepository dispatcherRepository = RepositorySinglePointAccess.getDispatcherRepository();
    @Override
    public Dispatcher save(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    @Override
    public Dispatcher update(Dispatcher dispatcher) {
        return dispatcherRepository.update(dispatcher);
    }

    @Override
    public List<Dispatcher> findAll() {
        return dispatcherRepository.findAll();
    }

    @Override
    public Dispatcher findById(Integer id) {
        return dispatcherRepository.findById(id);
    }

    @Override
    public boolean delete(Dispatcher dispatcher) {
        return dispatcherRepository.delete(dispatcher);
    }
}
