package project.weather.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weatherList")
@NoArgsConstructor

public class WeatherListController {

    @GetMapping
    public String getWeatherList() {



        return "weatherList";
    }
}
