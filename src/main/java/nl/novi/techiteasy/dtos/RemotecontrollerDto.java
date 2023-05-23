package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;

public class RemotecontrollerDto {

    @NotBlank
    public Long id;

    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public double price;
    public int originalStock;


}
