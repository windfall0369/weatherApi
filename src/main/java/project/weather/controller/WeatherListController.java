package project.weather.controller;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.weather.Weather;

@Controller
@RequiredArgsConstructor

public class WeatherListController {

    private final EntityManager em;



    @GetMapping("/weatherList")
    public String getWeatherList(Model model) {

        Object singleResult = em.createQuery("select count (w.id) FROM Weather w").getSingleResult();
        em.createQuery("select w.id from Weather w where ")
        Long result = (Long) singleResult;
        System.out.println("리스트 SUM = " + result);

        model.addAttribute("key", result);
        return "weatherList";
    }


    @Transactional
    @PostMapping("/weatherList")
    public String deleteWeather(@RequestParam(value = "listId")Long key) {

        System.out.println("key(listId) = " + key);
        Weather weather1 = em.find(Weather.class, key);
        em.remove(weather1);
        System.out.println("============delete query complete===============");

        return "/weatherList";
    }


}
