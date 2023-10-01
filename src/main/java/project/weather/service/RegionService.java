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

    @Value("${localApiServiceKey}")
    private String localServiceKey;


    public LocationInfo getCoordinate(String address) {

        System.out.println("localServiceKey = " + localServiceKey);

        RestTemplate restTemplate = new RestTemplate();

        String apiKey = "KakaoAK " + localServiceKey;

        // 요청 헤더에 만들기, Authorization 헤더 설정하기
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriComponents = UriComponentsBuilder
            .fromHttpUrl(uri)
            .queryParam("query",address)
            .build();


        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

        // API Response json parsing
        String body = response.getBody();
        JSONObject json = new JSONObject(body);
        // body에서 좌표 추출
        JSONArray documents = json.getJSONArray("documents");

//        int x = (Integer.parseInt(documents.getJSONObject(0).getString("x"))/1);
//        int x = (int) (Long.parseLong(documents.getJSONObject(0).getString("x"))/1);

        Double x = Double.parseDouble(documents.getJSONObject(0).getString("x"));
        Double y = Double.parseDouble(documents.getJSONObject(0).getString("y"));

        x = x/1;
        y = y/1;

        int nx = x.intValue();
        int ny = y.intValue();





        String fullAddress = documents.getJSONObject(0).getString("address_name");

        String region1 = documents.getJSONObject(0).getJSONObject("address")
            .getString("region_1depth_name");

        String region2 = documents.getJSONObject(0).getJSONObject("address")
            .getString("region_2depth_name");

        String region3 = documents.getJSONObject(0).getJSONObject("address")
            .getString("region_3depth_name");


        System.out.println("address = " + fullAddress);
        System.out.println("region1 = " + region1);
        System.out.println("region2 = " + region2);
        System.out.println("region3 = " + region3);
        System.out.println("x = " + nx);
        System.out.println("y = " + ny);

        return new LocationInfo(nx, ny, region1, region2, region3);
    }


}
