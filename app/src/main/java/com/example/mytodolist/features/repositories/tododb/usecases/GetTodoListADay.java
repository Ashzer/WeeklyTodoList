package com.example.mytodolist.features.repositories.tododb.usecases;

import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.repositories.entities.TodoEntity;
import com.example.mytodolist.features.ui.home.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class GetTodoListADay {
    @Inject
    TodoListRepository repository;

    @Inject
    public GetTodoListADay(){}

    public List<Todo> execute(String date){
        List<TodoEntity> list = repository.getTodoListADay(date);
        if(list!=null){
            return repository.getTodoListADay(date).stream().map(todoEntity -> todoEntity.toTodo()).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }
}
