package project.weather.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.weather.Region;


@Controller
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class ResetRegionController {


    private final EntityManager em;


    @PostMapping
    @Transactional
    public String resetRegionList() {

        Path path = Paths.get("../weatherApi.csv").toAbsolutePath();
        System.out.println("path = " + path.toAbsolutePath().toString());

        URI uri = path.toUri();

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new UrlResource(uri).getInputStream()))
        ) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] splits = line.split(",");
                em.persist(new Region(Long.parseLong(splits[0]),splits[1],splits[2],
                    Integer.parseInt(splits[3]),Integer.parseInt(splits[4])));
            }
        } catch (
            IOException e) {
            e.printStackTrace();
            System.out.println("error");
            return "index";

        }

        System.out.println("성공");
        return "resetSuccess";
    }



}
