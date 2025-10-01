package at.technikum.application.todo.exception;

import at.technikum.server.http.ContentType;
import at.technikum.server.http.Response;
import at.technikum.server.http.Status;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper {

    private final Map<Class<?>, Response> map;

    public ExceptionMapper() {
        this.map = new HashMap<>();
    }

    public Response toResponse(Exception exception) {
        Response response = new Response();

        /*
        if (exception instanceof EntityNotFoundException) {
            response.setStatus(Status.NOT_FOUND);
            response.setContentType(ContentType.TEXT_PLAIN);
            response.setBody(exception.getMessage());

            return response;
        }
        */

        /*
        Annotation annotation = exception.getClass().getAnnotation(HttpStatus.class)
        if (null != annotation) {
            Status status = annotation.status()

            // send status as response
        }

         */

        response = map.get(exception.getClass());

        if (null != response) {
            response.setBody(exception.getMessage());
            return response;
        }


        response.setStatus(Status.INTERNAL_SERVER_ERROR);
        response.setContentType(ContentType.TEXT_PLAIN);
        response.setBody(exception.getMessage());

        return response;
    }

    public void register(Class<?> clazz, Response response) {
        map.put(clazz, response);
    }
}
