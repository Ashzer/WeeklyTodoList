package com.example.mytodolist.features.repositories.tododb.usecases;

import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.repositories.entities.TodoEntity;
import com.example.mytodolist.features.ui.home.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class GetTodoListUseCase {
    @Inject
    TodoListRepository repository;

    @Inject
    public GetTodoListUseCase() {
    }

    public List<Todo> execute() {
        List<TodoEntity> list = repository.getTodoList();
        if (list != null) {
            return repository.getTodoList().stream().map(todoEntity -> todoEntity.toTodo()).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
