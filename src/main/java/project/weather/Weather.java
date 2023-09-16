package project.weather;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public double getRainAmount() {
        return rainAmount;
    }

    public void setRainAmount(int rainAmount) {
        this.rainAmount = rainAmount;
    }

    public double getHumid() {
        return humid;
    }

    public void setHumid(int humid) {
        this.humid = humid;
    }

    public String getLastUpdateTIme() {
        return lastUpdateTIme;
    }

    public void setLastUpdateTIme(String lastUpdateTIme) {
        this.lastUpdateTIme = lastUpdateTIme;
    }
}

