package project.weather.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RegionService {

    private final String uri = "https://dapi.kakao.com/v2/local/search/address.json";

    @Value("${serviceKey}")
    private String localServieKey;


    public GetNxNy getCoordinate(String addresss){
        RestTemplate restTemplate = new RestTemplate();

        String apiKey = "KakaoAK " + localServieKey;
        String location = addresss;

        // 요청 헤더에 만들기, Authorization 헤더 설정하기
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriComponents = UriComponentsBuilder
            .fromHttpUrl(uri)
            .queryParam("query",location)
            .build();

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

        // API Response json parsing
        String body = response.getBody();
        JSONObject json = new JSONObject(body);
        // body에서 좌표 추출
        JSONArray documents = json.getJSONArray("documents");
        int x = (Integer.parseInt(documents.getJSONObject(0).getString("x"))/1);
        int y = (Integer.parseInt(documents.getJSONObject(0).getString("y"))/1);

        System.out.println("x = " + x);
        System.out.println("y = " + y);

        return new GetNxNy(x, y);
    }


}
