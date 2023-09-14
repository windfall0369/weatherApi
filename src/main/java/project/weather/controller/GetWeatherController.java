package project.weather.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.weather.Region;
import project.weather.Weather;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GetWeatherController {

    private final EntityManager em;

    private String serviceKey = "kQkDPvw2TDmPAFD7HvgUb31WyyKpPrzI%2BH%2BXoELvejXjWxJb1H5gIaZAdwhv%2FjuqyJ9OSdPYQYSCKhKEp3E7TA%3D%3D";

//    http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst
//        ?serviceKey=kQkDPvw2TDmPAFD7HvgUb31WyyKpPrzI%2BH%2BXoELvejXjWxJb1H5gIaZAdwhv%2FjuqyJ9OSdPYQYSCKhKEp3E7TA%3D%3D&numOfRows=10&pageNo=1&dataType=JSON
//        &base_date=20230910&base_time=0600&nx=55&ny=127


    @GetMapping("/getWeather")
    public String getWeather() {

        System.out.println("getMapping 작동");
        return "index";

    }

    @PostMapping("/getWeather")
    @Transactional
    public String getWeather(@RequestParam(required = false, name = "location") Integer location)
        throws IOException {

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

//        nx = (regionInfo.getNx());
//        ny = (regionInfo.getNy());

        nx = 60;
        ny = 127;

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

            String temp = null;
            String humid = null;
            String rainAmount = null;

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            for (int i = 0; i < item.size(); i++) {
                JSONObject obj = (JSONObject) item.get(i);
                String category = (String) obj.get("category");
                String obsrValue = (String) obj.get("obsrValue");

                switch (category) {
                    case "T1H":
                        temp = obsrValue;
                        break;
                    case "RN1":
                        rainAmount = obsrValue;
                        break;
                    case "REH":
                        humid = obsrValue;
                        break;
                }
            }

            Weather weather = new Weather(temp, rainAmount, humid, currentChangeTime);

            System.out.println("location 의 현재 날씨 = " + location);
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


    }

}
