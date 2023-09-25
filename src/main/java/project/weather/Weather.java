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
@Entity
@AllArgsConstructor
public class Weather {



    @Id
    @GeneratedValue
    private Long id;

    private String region1;
    private String region2;
    private String region3;
    private double temp;
    private double rainAmount;
    private double humid;
    private String lastUpdateTIme;

}

