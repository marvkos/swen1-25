package at.technikum.application.todo.repository;

import at.technikum.application.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public class MemoryTodoRepository implements TodoRepository {
    @Override
    public Optional<Todo> find(String id) {
        return Optional.empty();
    }

    @Override
    public List<Todo> findAll() {
        return List.of();
    }

    @Override
    public Todo save(Todo todo) {
        return todo;
    }

    @Override
    public Todo delete(String id) {
        return null;
    }
}
