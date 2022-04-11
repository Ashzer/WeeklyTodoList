package com.example.mytodolist.features.repositories.tododb.usecases;

import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.ui.home.Todo;

import javax.inject.Inject;

public class SaveTodoUseCase {
    @Inject
    TodoListRepository repository;

    @Inject
    public SaveTodoUseCase() {
    }

    public long execute(Todo todo) {
        return repository.saveTodo(todo.toTodoEntity());
    }

}
