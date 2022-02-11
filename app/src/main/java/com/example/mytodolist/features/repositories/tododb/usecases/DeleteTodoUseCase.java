package com.example.mytodolist.features.repositories.tododb.usecases;

import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.ui.home.Todo;

import javax.inject.Inject;

public class DeleteTodoUseCase {
    @Inject
    TodoListRepository repository;

    @Inject
    DeleteTodoUseCase(){}

    public void execute(Todo todo){
        repository.deleteTodo(todo.toTodoEntity());
    }
}
