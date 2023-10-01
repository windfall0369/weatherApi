package project.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:/api.properties")
public class WeatherApplication {




	public static void main(String[] args) throws Exception {


		SpringApplication.run(WeatherApplication.class, args);






	}

}
