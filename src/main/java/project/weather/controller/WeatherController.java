package project.weather.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/weather")
@NoArgsConstructor
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
