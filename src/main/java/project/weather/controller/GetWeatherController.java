package project.weather.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.weather.Region;
import project.weather.Weather;
import project.weather.dto.WeatherForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class GetWeatherController {

    private final EntityManager em;



    //서비스키는 환경변수로 선언, 별개의 파일로 application.yml
    //알고 써야됨, 역할을 아는가
    //경쟁력 = 아는가 / 모르는가 차이
    //장,단 부작용, 용도 등



//    private String serviceKey = "kQkDPvw2TDmPAFD7HvgUb31WyyKpPrzI%2BH%2BXoELvejXjWxJb1H5gIaZAdwhv%2FjuqyJ9OSdPYQYSCKhKEp3E7TA%3D%3D";

    //    http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst
//        ?serviceKey=kQkDPvw2TDmPAFD7HvgUb31WyyKpPrzI%2BH%2BXoELvejXjWxJb1H5gIaZAdwhv%2FjuqyJ9OSdPYQYSCKhKEp3E7TA%3D%3D&numOfRows=10&pageNo=1&dataType=JSON
//        &base_date=20230910&base_time=0600&nx=55&ny=127



    @Value("${serviceKey}")
    private final String serviceKey;


    //접근 제어자 private/ public
    //static  class 변수
    //디테일

    @GetMapping("/getWeather")
    @Transactional
    public String getWeather(WeatherForm weatherForm) throws IOException {

        Long location = Long.parseLong(weatherForm.getLocation());

        log.info("location = " + location);
        System.out.println("location = " + location);

        StringBuilder urlBuilder = new StringBuilder(
            "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");

        //시간
        LocalDateTime now = LocalDateTime.now();
        String yyyyMMdd = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int hour = now.getHour();
        int min = now.getMinute();
        if (min <= 40) {
            hour -= 1;
        }

        String hourStr = hour + "00";
        int nx;
        int ny;
        String currentChangeTime = now.format(DateTimeFormatter.ofPattern("yy.MM.dd ")) + hour;

        Region regionInfo = em.find(Region.class, location);

        nx = (regionInfo.getNx());
        ny = (regionInfo.getNy());

        String region1 = regionInfo.getRegion1();
        String region2 = regionInfo.getRegion2();



        https://dapi.kakao.com/v2/local/search/address.json?



        try {
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
            urlBuilder.append(
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10",
                    "UTF-8"));
            urlBuilder.append(
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append(
                "&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON",
                    "UTF-8"));
            urlBuilder.append(
                "&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(yyyyMMdd,
                    "UTF-8"));
            urlBuilder.append(
                "&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(hourStr,
                    "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + nx);
            urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + ny);

            URL url = new URL(urlBuilder.toString());
            log.info("request url : {}", url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Context-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            String data = sb.toString();

            System.out.println("=============================");
            System.out.println("data = " + data);
            System.out.println("=============================");

            double temp =0;
            double humid = 0;
            double rainAmount = 0;

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");



            //변수명 (디테일)
            for (int i = 0; i < item.size(); i++) {
                JSONObject obj = (JSONObject) item.get(i);
                String category = (String) obj.get("category");
                String obsrValue = (String) obj.get("obsrValue");

                switch (category) {
                    case "T1H":
                        temp = Double.parseDouble(obsrValue);
                        break;
                    case "RN1":
                        rainAmount = Double.parseDouble(obsrValue);
                        break;
                    case "REH":
                        humid = Double.parseDouble(obsrValue);
                        break;
                }

                //switch , if 차이
            }




//            Weather weather = new Weather(temp, rainAmount, humid, currentChangeTime);
            final Weather weather = new Weather(region1,region2,temp, rainAmount, humid, currentChangeTime);


            System.out.println("위치 = " + location);
            System.out.println("weather.getRegion1() = " + weather.getRegion1());
            System.out.println("weather.getRegion2() = " + weather.getRegion2());
            System.out.println("weather.getTemp() = " + weather.getTemp());
            System.out.println("weather.getHumid() = " + weather.getHumid());
            System.out.println("weather.getRainAmount() = " + weather.getRainAmount());
            System.out.println("weather.getLastUpdateTIme() = " + weather.getLastUpdateTIme());

            return "getWeather";

        } catch (IOException e) {
            System.out.println("error");
            return "error";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //service - controller 분리
        //계층 분리

    }

    @PostMapping("/getWeather")
    public String saveWeather(Weather weather) {
        em.persist(weather);

        return "index";
    }






}
