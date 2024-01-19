package app.dto;

import lombok.Data;

import java.awt.desktop.AboutEvent;

@Data
public class DispatcherDTO {
    private String name, phone, address;
    private boolean inApel;
    private Integer salary;
}
