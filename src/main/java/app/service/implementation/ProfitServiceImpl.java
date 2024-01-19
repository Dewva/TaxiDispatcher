package app.service.implementation;

import app.model.Profit;
import app.repository.ProfitRepository;
import app.service.ProfitService;
import app.single_point_access.RepositorySinglePointAccess;

import java.util.Date;
import java.util.List;

public class ProfitServiceImpl implements ProfitService {
    private ProfitRepository profitRepository = RepositorySinglePointAccess.getProfitRepository();
    @Override
    public Profit save(Profit profit) {
        return profitRepository.save(profit);
    }

    @Override
    public Profit update(Profit profit) {
        return profitRepository.update(profit);
    }

    @Override
    public List<Profit> findAll() {
        return profitRepository.findAll();
    }

    @Override
    public Profit findById(Integer id) {
        return profitRepository.findById(id);
    }

    @Override
    public boolean delete(Profit profit) {
        return profitRepository.delete(profit);
    }

    @Override
    public Profit findByDate(Date date) {
        return profitRepository.findProfitByDate(date);
    }
}
