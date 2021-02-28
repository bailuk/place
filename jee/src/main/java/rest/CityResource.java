package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import entity.City;
import service.DbService;


@Path("city")
public class CityResource {

	private final Logger log = Logger.getLogger(CityResource.class);
	
	@Inject
	private DbService dbService;
	


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
                result = dbService.getEm().createNamedQuery("City.findById", City.class).setParameter("id", Integer.valueOf(id)).getResultList();
                
            } else if (! "".equals(plz)) {
                result = dbService.getEm().createNamedQuery("City.findByPlz", City.class).setParameter("plz", plz).getResultList();

            } else if (! "".equals(name) ) {
                result = dbService.getEm().createNamedQuery("City.findByName", City.class).setParameter("name", "%" + name + "%").getResultList();    

            } else if (! "".equals(city) ) {
                result = dbService.getEm().createNamedQuery("City.findByCity", City.class).setParameter("city", city).getResultList();

            } else {
                result = dbService.getEm().createNamedQuery("City.findAll", City.class).getResultList();

            }
            return Response.ok(result).build();

        } catch (Exception e) {
        	log.error(e);
            return ErrorResponse.getResponse(e);
            
        }
    }
}
