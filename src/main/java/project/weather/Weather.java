package project.weather;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class Weather {


    public Weather(String region1, String region2, double temp, double rainAmount, double humid,
        String lastUpdateTIme) {
        this.region1 = region1;
        this.region2 = region2;
        this.temp = temp;
        this.rainAmount = rainAmount;
        this.humid = humid;
        this.lastUpdateTIme = lastUpdateTIme;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String region1;
    private String region2;
    private double temp;
    private double rainAmount;
    private double humid;
    private String lastUpdateTIme;

}

