package project.weather.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {


    @PersistenceContext
    private EntityManager em;

}
