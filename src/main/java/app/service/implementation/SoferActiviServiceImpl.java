package app.service.implementation;

import app.model.SoferActivi;
import app.repository.SoferActiviRepository;
import app.service.SoferActiviService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class SoferActiviServiceImpl implements SoferActiviService {
    private SoferActiviRepository soferActiviRepository = RepositorySinglePointAccess.getSoferActiviRepository();
    @Override
    public SoferActivi save(SoferActivi soferActivi) {
        return soferActiviRepository.save(soferActivi);
    }

    @Override
    public SoferActivi update(SoferActivi soferActivi) {
        return soferActiviRepository.update(soferActivi);
    }

    @Override
    public List<SoferActivi> findAll() {
        return soferActiviRepository.findAll();
    }

    @Override
    public SoferActivi findById(Integer id) {
        return soferActiviRepository.findById(id);
    }

    @Override
    public boolean delete(SoferActivi soferActivi) {
        return soferActiviRepository.delete(soferActivi);
    }
}
