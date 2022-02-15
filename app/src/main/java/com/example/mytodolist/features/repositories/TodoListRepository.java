package com.example.mytodolist.features.repositories;

import com.example.mytodolist.features.repositories.entities.TodoEntity;

import java.util.List;

public interface TodoListRepository {
    List<TodoEntity> getTodoList();
    long saveTodo(TodoEntity todo);
    void deleteTodo(TodoEntity todo);
    int updateTodo(TodoEntity todo);
    List<TodoEntity> getTodoListADay(String date);
}
