package project.weather.dto;


//dto 쓰는 이유
//dao 등
//추상적 개념

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WeatherForm {


    private String region1;
    private String region2;
    private String region3;
    private String temp;
    private String  humid;
    private String  rainAmount;
    private String lastUpdateTime;



}
