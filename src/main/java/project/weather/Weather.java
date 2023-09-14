package project.weather;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Weather {

    public Weather(String temp, String rainAmount, String humid, String lastUpdateTIme) {
        this.temp = temp;
        this.rainAmount = rainAmount;
        this.humid = humid;
        this.lastUpdateTIme = lastUpdateTIme;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String temp;
    private String rainAmount;
    private String humid;
    private String lastUpdateTIme;


    public void setNy(String ny) {
        this.ny = ny;
    }

    private String nx;
    private String ny;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRainAmount() {
        return rainAmount;
    }

    public void setRainAmount(String rainAmount) {
        this.rainAmount = rainAmount;
    }

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    public String getLastUpdateTIme() {
        return lastUpdateTIme;
    }

    public void setLastUpdateTIme(String lastUpdateTIme) {
        this.lastUpdateTIme = lastUpdateTIme;
    }
}
