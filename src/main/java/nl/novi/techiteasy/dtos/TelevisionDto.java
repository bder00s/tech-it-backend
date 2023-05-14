package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.novi.techiteasy.models.Television;


//inputDTO wordt gebruikt bij PostMapping/ Postrequests

//outputDTO wordt gebruikt bij GetMapping / Getrequests


public class TelevisionDto {

    public Long id;
    @NotBlank
    public String type;
    public String brand;
    @NotBlank
    public String name;
    @Min(value = 1)
    public double price;




    public static TelevisionDto fromTelevision(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.id = television.getId();
        dto.type = television.getType();
        dto.brand = television.getBrand();
        dto.name = television.getName();
        dto.price = television.getPrice();
        return dto;
    }

}
