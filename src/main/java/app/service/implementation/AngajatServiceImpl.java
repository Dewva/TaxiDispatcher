package app.service.implementation;

import app.model.Angajat;
import app.repository.AngajatRepository;
import app.service.AngajatService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.List;

public class AngajatServiceImpl implements AngajatService {
    private AngajatRepository angajatRepository = RepositorySinglePointAccess.getAngajatRepository();
    @Override
    public Angajat save(Angajat angajat) {
        return angajatRepository.save(angajat);
    }

    @Override
    public Angajat update(Angajat angajat) {
        return angajatRepository.update(angajat);
    }

    @Override
    public List<Angajat> findAll() {
        return angajatRepository.findAll();
    }

    @Override
    public Angajat findById(Integer id) {
        return angajatRepository.findById(id);
    }

    @Override
    public boolean delete(Angajat angajat) {
        return angajatRepository.delete(angajat);
    }
}
