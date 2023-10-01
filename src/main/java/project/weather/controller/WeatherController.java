package project.weather.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.weather.Weather;
import project.weather.dto.RegionForm;
import project.weather.service.LocationInfo;
import project.weather.service.RegionService;
import project.weather.service.WeatherService;

@Slf4j
@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final RegionService regionService;



    @GetMapping
    public String getWeather() {
        System.out.println("GetMapping -> searchRegion.html");
        return "searchRegion";
    }


    @PostMapping
    public String getWeather(RegionForm regionForm, Model model) {

        System.out.println("PostMapping -> weatherForecast.html");

        String address = regionForm.getAddress();

        LocationInfo location = regionService.getCoordinate(address);

        System.out.println("regionService.getCoordinate(address) 실행");

        Weather weather = weatherService.readWeather(location);

        System.out.println("weatherService.readWeather(location) 실행");
        log.info("weather = {}",weather);

        //text box에서 주소 받아옴 -> local Api로 좌표 추출 -> 기상청 Api로 날씨 조회 및  weather 반환

        model.addAttribute("id",weather.getId());
        model.addAttribute("region1", weather.getRegion1());
        model.addAttribute("region2", weather.getRegion2());
        model.addAttribute("region3", weather.getRegion3());
        model.addAttribute("temp", weather.getTemp());
        model.addAttribute("rainAmount", weather.getRainAmount());
        model.addAttribute("humid", weather.getHumid());
        model.addAttribute("lastUpdateTime", weather.getLastUpdateTime());


        return "weatherForecast";
    }













}
