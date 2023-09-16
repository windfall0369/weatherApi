package project.weather.controller;

import javax.persistence.EntityManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.weather.Weather;


@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {





    @GetMapping
    public String getWeather() {
        return "weather";
    }



//    @PostMapping
//    public String updateWeather(@RequestParam("location") int location) {
//
//    }



    //@RequestParam 으로 받아오고 저장
    // @ModelAttribute 로 저장한 거 확인
}
