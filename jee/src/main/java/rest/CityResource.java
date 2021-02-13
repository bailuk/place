package rest;

import entity.City;
import service.CityService;

import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/")
public class CityResource {
    
    @Inject
    private CityService cityService;

    @GET
    @Path("city/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        try {
            City city = cityService.searchById(id);
            if (city != null) {
                return Response.ok(city).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("bad id").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

  
    @GET
    @Path("city")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<City> cities = cityService.getAll();
            return Response.ok(cities).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
