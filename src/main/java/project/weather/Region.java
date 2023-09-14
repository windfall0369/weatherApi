package project.weather;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Region {


    @Id
    private Long id;

    private String region1;
    private String region2;
    private int nx;
    private int ny;


    public Region(Long id, String region1, String region2, int nx, int ny) {
        this.id = id;
        this.region1 = region1;
        this.region2 = region2;
        this.nx = nx;
        this.ny = ny;
    }


    @Override
    public String toString() {
        return region1 + region2;

    }


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

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

    public void updateRegionWeather(Weather weather) {
    }
}
