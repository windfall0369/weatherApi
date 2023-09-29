package project.weather.dto;


//dto 쓰는 이유
//dao 등
//추상적 개념

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForm {


    private String region1;
    private String region2;
    private String region3;
    private double temp;
    private double humid;
    private double rainAmount;
    private String lastUpdateTime;





}
