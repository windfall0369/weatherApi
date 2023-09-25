package project.weather.controller;

import javax.persistence.EntityManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.weather.Weather;
import project.weather.dto.RegionForm;
import project.weather.dto.WeatherForm;
import project.weather.service.GetNxNy;
import project.weather.service.RegionService;
import project.weather.service.WeatherService;


@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {


    @GetMapping
    public String getWeather(RegionForm regionForm) {

        String address = regionForm.getAddress();

        RegionService regionService = new RegionService();
        GetNxNy location = regionService.getCoordinate(address);

        WeatherService weatherService = new WeatherService();
        weatherService.readWeather(location);


        return "weather";
    }






}
