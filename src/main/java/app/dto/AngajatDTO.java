package app.dto;

import app.model.Taxi;
import lombok.Data;

@Data
public class AngajatDTO {
    private String name, phone, address;
    private Integer salary;
    private boolean timeOfWork;
    private Taxi taxi;
}
