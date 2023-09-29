package project.weather.controller;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/weatherList")
public class WeatherListController {

    private final WeatherRepository weatherRepository;
    private final EntityManager em;


    @GetMapping
    public String getWeatherList(Model model) {

        List weatherList = em.createQuery("select w from Weather w ").getResultList();

        Object singleResult = em.createQuery("select w from Weather w where w.id=1L").getSingleResult();

        

        return "weatherList";
    }


    @PostMapping
    public String saveWeather(WeatherForm weatherForm) {

        System.out.println("PostMapping -> weatherList.html");

        Weather weather = new Weather();

        weather.setRegion1(weatherForm.getRegion1());
        weather.setRegion2(weatherForm.getRegion2());
        weather.setRegion3(weatherForm.getRegion3());
        weather.setTemp(weatherForm.getTemp());
        weather.setRainAmount(weather.getRainAmount());
        weather.setHumid(weatherForm.getHumid());
        weather.setLastUpdateTime(weatherForm.getLastUpdateTime());

        weatherRepository.save(weather);



        return "/weatherList";
    }


}
