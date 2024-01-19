package app.repository;

import app.model.SoferActivi;
import io.swagger.models.auth.In;

import java.util.List;

public interface SoferActiviRepository extends CRUDRepository<SoferActivi, Integer> {
    SoferActivi findSoferActiviByName(String name);
    List<SoferActivi> findFreeSoferActivi();
    Integer findCurrentProfit();
}
