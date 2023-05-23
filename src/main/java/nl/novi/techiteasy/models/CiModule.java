package nl.novi.techiteasy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ci_modules")
public class CiModule {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "television_id")
    private Television television;

    public CiModule(){

    }

}
