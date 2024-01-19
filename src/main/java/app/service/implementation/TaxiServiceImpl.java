package app.service.implementation;

import app.model.Taxi;
import app.repository.TaxiRepository;
import app.service.TaxiService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class TaxiServiceImpl implements TaxiService {
    TaxiRepository taxiRepository = RepositorySinglePointAccess.getTaxiRepository();

    @Override
    public Taxi save(Taxi taxi) {
        return taxiRepository.save(taxi);
    }

    @Override
    public Taxi update(Taxi taxi) {
        return taxiRepository.update(taxi);
    }

    @Override
    public List<Taxi> findAll() {
        return taxiRepository.findAll();
    }

    @Override
    public Taxi findById(Integer id) {
        return taxiRepository.findById(id);
    }

    @Override
    public boolean delete(Taxi taxi) {
        return taxiRepository.delete(taxi);
    }

    @Override
    public Taxi findByName(String name) {
        return taxiRepository.findByNrInmatriculare(name);
    }
}
