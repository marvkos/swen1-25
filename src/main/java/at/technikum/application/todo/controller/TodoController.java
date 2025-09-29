package at.technikum.application.todo.controller;

import at.technikum.application.common.Controller;
import at.technikum.application.todo.model.Todo;
import at.technikum.application.todo.service.TodoService;
import at.technikum.server.http.Method;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

import java.util.List;

public class TodoController extends Controller {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public Response handle(Request request) {

        if (request.getMethod().equals(Method.GET.getVerb())) {
            if (request.getPath().equals("/todos")) {
                return readAll();
            }
            return read();
        }

        if (request.getMethod().equals(Method.POST.getVerb())) {
            return create(request);
        }

        if (request.getMethod().equals(Method.PUT.getVerb())) {
            return update();
        }

        if (request.getMethod().equals(Method.DELETE.getVerb())) {
            return delete();
        }

        return null;
    }

    private Response readAll() {
        List<Todo> todos = todoService.getAll();

        text(todos.toString());
    }

    private Response read() {

    }

    private Response create(Request request) {

    }

    private Response update() {

    }

    private Response delete() {

    }
}
