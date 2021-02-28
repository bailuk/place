package service;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ApplicationScoped
public class DbService {
	@PersistenceContext(unitName = "PlacePU")
    private EntityManager em;
	
	
	public EntityManager getEm() {
		return em;
	}
}
