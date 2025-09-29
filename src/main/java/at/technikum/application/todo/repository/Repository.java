package at.technikum.application.todo.repository;

import at.technikum.application.todo.model.Todo;

import java.util.Optional;

public interface Repository<T, ID> {

    Optional<T> find(ID id);
}
