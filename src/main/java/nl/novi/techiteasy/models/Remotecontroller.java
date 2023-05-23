package nl.novi.techiteasy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "remote_controllers")
public class Remotecontroller {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "remotecontroller")
    // Television is hier de eigenaar van de relatie
    // remotecontroller_id zal in Pg Admin bij de Television tabel komen te staan
    private Television television;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private double price;
    private int stock;

    public Remotecontroller(){

    }

}
