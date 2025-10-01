package at.technikum.application.todo;

import at.technikum.application.common.Application;
import at.technikum.application.common.Controller;
import at.technikum.application.common.Router;
import at.technikum.application.todo.controller.TodoController;
import at.technikum.application.todo.exception.EntityNotFoundException;
import at.technikum.application.todo.exception.ExceptionMapper;
import at.technikum.application.todo.repository.MemoryTodoRepository;
import at.technikum.application.todo.service.TodoService;
import at.technikum.server.http.ContentType;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import at.technikum.server.http.Status;

public class TodoApplication implements Application {

    private final Router router;
    private final ExceptionMapper exceptionMapper;

    public TodoApplication() {
        this.router = new Router();

        router.addRoute("/todos", new TodoController(new TodoService(new MemoryTodoRepository())));

        this.exceptionMapper = new ExceptionMapper();
        Response response = new Response();
        response.setStatus(Status.INTERNAL_SERVER_ERROR);
        response.setContentType(ContentType.TEXT_PLAIN);

        this.exceptionMapper.register(EntityNotFoundException.class, response /* Response(404) */);
    }

    @Override
    public Response handle(Request request) {
        try {
            Controller controller = router.findController(request.getPath())
                    .orElseThrow(RuntimeException::new);

            return controller.handle(request);
        } catch (Exception ex) {
            // map exception to http response
        }
    }
}
