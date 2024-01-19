package app.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ProfitDTO {
    private Integer incasare, cheltuieli, profit;
    private Date data;
}
