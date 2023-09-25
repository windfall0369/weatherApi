package project.weather.dto;


//dto 쓰는 이유
//dao 등
//추상적 개념

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForm {

    private String address;


    public WeatherForm(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "address" + address;
    }

}
