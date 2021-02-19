package rest;

import entity.Student;

import org.jboss.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
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


@Path("student")
public class StudentResource {

    @PersistenceContext(unitName = "PlacePU")
    private EntityManager em;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get (
        @QueryParam("id")   @DefaultValue("") String id,
        @QueryParam("name") @DefaultValue("") String name
    ) {

        try {
            List<Student> result;

            if (! "".equals(id)) {
                result = em.createNamedQuery("Student.findById", Student.class).setParameter("id", Integer.valueOf(id)).getResultList();
                
            } else if (! "".equals(name) ) {
                result = em.createNamedQuery("Student.findByName", Student.class).setParameter("name", "%" + name + "%").getResultList();    

            } else {
                result = em.createNamedQuery("Student.findAll", Student.class).getResultList();

            }
            return Response.ok(result).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
            
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create (Student student) {
        em.persist(student);
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (Student student, @QueryParam("id") Integer id) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete (Student student, @QueryParam("id") Integer id) {
        return Response.status(Response.Status.BAD_REQUEST).build();        
    }

}
