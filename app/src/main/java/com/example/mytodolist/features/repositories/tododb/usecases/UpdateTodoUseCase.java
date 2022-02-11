package com.example.mytodolist.features.repositories.tododb.usecases;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.ui.home.Todo;

import javax.inject.Inject;

public class UpdateTodoUseCase {
    @Inject
    TodoListRepository repository;

    @Inject
    public UpdateTodoUseCase(){}

    public int execute(Todo todo){
        return repository.updateTodo(todo.toTodoEntity());
    }
}
