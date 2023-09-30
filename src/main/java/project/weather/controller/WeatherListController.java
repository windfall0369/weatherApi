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
    public String deleteWeather(WeatherForm weatherForm, @RequestParam(name = "weatherId")String id) {

        Long weatherId = Long.valueOf(id);
        weatherRepository.delete(weatherId);

        

        return "weatherList";
    }


}
