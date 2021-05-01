package rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import service.HelloService;

@Path("/hello")
public class HelloResource {
    
    @Inject
    private HelloService hello;

    @GET
    @Produces("text/plain")
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return hello.sayHello(name);
    }
}
