package nl.novi.techiteasy.models;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;


@Entity
@Setter
@Getter
@Table(name = "televisions")

public class Television {
//Id kiezen die duidelijk aangeeft waar het over gaat, bijvoorbeeld naam
    @Id
    @GeneratedValue
    private Long id;

    private String type;
    private String brand;
    private String name;
    private double price;
    private double availableSize;
    private double refreshRate;
    private String screenType;
    private String screenQuality;
    private boolean smartTv;
    private boolean wifi;
    private boolean voiceControl;
    private boolean hdr;
    private boolean bluetooth;
    private boolean ambiLight;
    private int originalStock;
    private int sold;


    public Television(Long id, String type, String brand, String name, double price, double availableSize, double refreshRate, String screenType, String screenQuality, boolean smartTv, boolean wifi, boolean voiceControl, boolean hdr, boolean bluetooth, boolean ambiLight, int originalStock, int sold) {
            this.id = id;
            this.type = type;
            this.brand = brand;
            this.name = name;
            this.price = price;
            this.availableSize = availableSize;
            this.refreshRate = refreshRate;
            this.screenType = screenType;
            this.screenQuality = screenQuality;
            this.smartTv = smartTv;
            this.wifi = wifi;
            this.voiceControl = voiceControl;
            this.hdr = hdr;
            this.bluetooth = bluetooth;
            this.ambiLight = ambiLight;
            this.originalStock = originalStock;
            this.sold = sold;
    }

    public Television() {

    }

}