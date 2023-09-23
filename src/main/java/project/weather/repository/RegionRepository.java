package project.weather.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import project.weather.Region;

@Repository
public class RegionRepository {


    @PersistenceContext
    private EntityManager em;


    public void save(Region region) {
        em.persist(region);
    }


    public Region findRegion(Long id) {
        return em.find(Region.class, id);
    }

    public List<Region> findAll() {
        return em.createQuery("select m from Region m", Region.class)
            .getResultList();
    }

    public List<Region> findByName(String name) {
        return em.createQuery("select m from Region m where m.region2 = : name", Region.class)
            .setParameter("name", name)
            .getResultList();
    }
}
