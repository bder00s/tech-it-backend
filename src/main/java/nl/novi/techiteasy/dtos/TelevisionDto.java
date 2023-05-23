package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.novi.techiteasy.models.CiModule;
import nl.novi.techiteasy.models.Remotecontroller;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;

import java.rmi.Remote;
import java.util.List;


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

public Remotecontroller remotecontroller;
public CiModule ciModule;
public List<WallBracket> wallbrackets;





    public static TelevisionDto fromTelevision(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.id = television.getId();
        dto.type = television.getType();
        dto.brand = television.getBrand();
        dto.name = television.getName();
        dto.price = television.getPrice();
        dto.remotecontroller = television.getRemotecontroller();
        return dto;
    }

}
