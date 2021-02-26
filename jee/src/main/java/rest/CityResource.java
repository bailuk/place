package rest;

import entity.City;

import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Path("city")
public class CityResource {

    @PersistenceContext(unitName = "PlacePU")
    private EntityManager em;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get (
        @QueryParam("id")   @DefaultValue("") String id,
        @QueryParam("plz")  @DefaultValue("") String plz,
        @QueryParam("name") @DefaultValue("") String name,
        @QueryParam("city") @DefaultValue("") String city
    ) {

        try {
            List<City> result;

            if (! "".equals(id)) {
                result = em.createNamedQuery("City.findById", City.class).setParameter("id", Integer.valueOf(id)).getResultList();
                
            } else if (! "".equals(plz)) {
                result = em.createNamedQuery("City.findByPlz", City.class).setParameter("plz", plz).getResultList();

            } else if (! "".equals(name) ) {
                result = em.createNamedQuery("City.findByName", City.class).setParameter("name", "%" + name + "%").getResultList();    

            } else if (! "".equals(city) ) {
                result = em.createNamedQuery("City.findByCity", City.class).setParameter("city", city).getResultList();

            } else {
                result = em.createNamedQuery("City.findAll", City.class).getResultList();

            }
            return Response.ok(result).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
            
        }
    }
}
