package project.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import project.weather.controller.ResetRegionController;


@EnableBatchProcessing
@SpringBootApplication
public class WeatherApplication {




	public static void main(String[] args) throws Exception {





		SpringApplication.run(WeatherApplication.class, args);






	}

}
