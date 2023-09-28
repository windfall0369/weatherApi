package project.weather.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.weather.Weather;

@Repository
@PersistenceContext
public class WeatherRepository {


    private EntityManager em;

    public void save(Weather weather) {
        em.persist(weather);
    }

    public Weather findOne(Long id) {
        return em.find(Weather.class,id);
    }

    public List<Weather> findAll() {
        return em.createQuery("select w from Weather w", Weather.class)
            .getResultList();
    }


}
