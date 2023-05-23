package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.techiteasy.models.Television;

public class CiModuleDto {

    @NotBlank
    public Long id;
    public String name;
    public double price;
    public String type;

    public Television television;


}
