package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import nl.novi.techiteasy.models.Television;

public class WallbracketDto {

    @NotBlank
    public Long id;
    public String size;
    public Boolean adjustable;
    public String name;
    public double price;

public Television television;

}
