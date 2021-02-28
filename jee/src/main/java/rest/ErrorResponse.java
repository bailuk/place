package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


public class ErrorResponse {

    public static Response getResponse(Exception exception) {
        String message = exception.getLocalizedMessage();
        
        if (message == null) {
            message = exception.getMessage();
        }
        
        if (message == null) {
        	message = exception.getClass().getSimpleName();
        }
        
        return getResponse(message);
    }


    public static Response getResponse(String message) {
        ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
        return builder.type(MediaType.TEXT_PLAIN)
                     .entity(message)
                     .build();
   }
}