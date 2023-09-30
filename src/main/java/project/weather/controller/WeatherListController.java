package project.weather.controller;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.weather.Weather;
import project.weather.dto.WeatherForm;
import project.weather.repository.WeatherRepository;


@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/weatherList")
public class WeatherListController {

    private final WeatherRepository weatherRepository;
    private final EntityManager em;


    @GetMapping
    public String getWeatherList(Model model) {

        log.info("GetMapping -> weatherList.html");

        List<Weather> weatherList = em.createQuery("select w from Weather w ").getResultList();

        model.addAttribute("weathers", weatherList);

        return "weatherList";
    }


    @PostMapping
    public String deleteWeather(@RequestParam(name = "weatherId") String weatherId) {

        System.out.println("weatherId = " + weatherId);
        Long id = Long.parseLong(weatherId);
        Weather weather = em.find(Weather.class, id);
        weatherRepository.delete(weather);



        return "redirect:weatherList";
    }


}
