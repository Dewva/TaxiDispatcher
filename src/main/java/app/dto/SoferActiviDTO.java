package app.dto;

import app.model.Angajat;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class SoferActiviDTO {
    private Angajat sofer;
    private Boolean inCursa;
    private Integer nrCurse, profit;
}
