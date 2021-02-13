package service;

import entity.City;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@ApplicationScoped
public class CityService {

    @PersistenceContext(unitName = "PlacePU")
    private EntityManager em;

    public City searchById(int id) {
        return em.createNamedQuery("City.findById", City.class).
                    setParameter("id", id).getSingleResult();
    }

    public List<City> getAll() {
        return em.createNamedQuery("City.findAll", City.class).getResultList();
    }
}

