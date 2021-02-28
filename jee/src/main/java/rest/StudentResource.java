package rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import entity.City;
import entity.Student;
import entity.StudentIn;
import service.DbService;


@Path("student")
public class StudentResource {

	@Inject
	private DbService dbService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get (
        @QueryParam("id")   @DefaultValue("") String id,
        @QueryParam("name") @DefaultValue("") String name
    ) {

        try {
            List<Student> result;

            if (! "".equals(id)) {
                result = dbService.getEm().createNamedQuery("Student.findById", Student.class).setParameter("id", Integer.valueOf(id)).getResultList();
                
            } else if (! "".equals(name) ) {
                result = dbService.getEm().createNamedQuery("Student.findByName", Student.class).setParameter("name", "%" + name + "%").getResultList();    

            } else {
                result = dbService.getEm().createNamedQuery("Student.findAll", Student.class).getResultList();

            }
            return Response.ok(result).build();

        } catch (Exception e) {
        	return ErrorResponse.getResponse(e);
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(StudentIn studentIn, @QueryParam("id") String id) {
    	try {
    		Student student = dbService.getEm().find(Student.class, Integer.valueOf(id));
    		
    		if (student == null) {
    			return ErrorResponse.getResponse("student not found");
    		}

    		City city = dbService.getEm().find(City.class, studentIn.id_city);
    		if (city == null) {
    			return ErrorResponse.getResponse("city not found");
    		}
    		
    		student.init(studentIn, city);
    		dbService.getEm().persist(student);
    		return Response.ok().build();
    		
    	} catch (Exception e) {
    		return ErrorResponse.getResponse(e);
    	}
    }
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(StudentIn studentIn, @Context UriInfo uriInfo) {
    	try {
    		City city = dbService.getEm().find(City.class, studentIn.id_city);
    		
    		if (city == null) {
    			return ErrorResponse.getResponse("city not found");
    		}
    			
    		Student student = new Student();
    		student.init(studentIn, city);
    		dbService.getEm().persist(student);
    
    		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
    		uriBuilder.queryParam("id", student.getId());
    		return Response.created(uriBuilder.build()).build();
    		
    		
    	} catch (Exception e) {
    		return ErrorResponse.getResponse(e);
    	}
    }

    
    @DELETE
    public Response delete(@QueryParam("id") String id) {
    	try {

    		Student student = dbService.getEm().find(Student.class, Integer.valueOf(id));
    		
    		if (student != null) {
            	dbService.getEm().remove(student);
            } else {
            	return ErrorResponse.getResponse("student not found");
            }
    		return Response.ok().build();
    		
    	} catch (Exception e) {
    		return ErrorResponse.getResponse(e);
    		
    	}
    }
}
