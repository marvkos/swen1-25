package at.technikum.application.todo;

import at.technikum.application.common.Application;
import at.technikum.application.common.Controller;
import at.technikum.application.todo.exception.RouteNotFoundException;
import at.technikum.application.common.Router;
import at.technikum.application.todo.controller.TodoController;
import at.technikum.application.todo.exception.*;
import at.technikum.application.todo.repository.MemoryTodoRepository;
import at.technikum.application.todo.service.TodoService;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import at.technikum.server.http.Status;

public class TodoApplication implements Application {

    private final Router router;
    private final ExceptionMapper exceptionMapper;

    public TodoApplication() {
        this.router = new Router();

        router.addRoute("/todos",
                new TodoController(
                    new TodoService(
                            new MemoryTodoRepository()
                    )
            )
        );
        // router.addRoute("/todos", "GET", TodoController::readALL)

        this.exceptionMapper = new ExceptionMapper();
        this.exceptionMapper.register(EntityNotFoundException.class, Status.NOT_FOUND);
        this.exceptionMapper.register(NotJsonBodyException.class, Status.BAD_REQUEST);
        this.exceptionMapper.register(JsonConversionException.class, Status.INTERNAL_SERVER_ERROR);
        this.exceptionMapper.register(RouteNotFoundException.class, Status.NOT_FOUND);
    }

    @Override
    public Response handle(Request request) {
        try {
            Controller controller = router.findController(request.getPath())
                    .orElseThrow(
                            () -> new RouteNotFoundException(
                                    "%s not found".formatted(request.getPath())
                            )
                    );

            return controller.handle(request);
        } catch (Exception ex) {
            return exceptionMapper.toResponse(ex);
        }
    }
}
