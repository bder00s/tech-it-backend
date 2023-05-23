package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "wall_bracket")
public class WallBracket {


    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private double price;

    @ManyToMany(mappedBy = "wallbrackets")
    List<Television> televisions;


    public WallBracket() {

    }
}
