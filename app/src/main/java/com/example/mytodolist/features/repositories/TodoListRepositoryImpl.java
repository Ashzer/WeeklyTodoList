package com.example.mytodolist.features.repositories;

import com.example.mytodolist.features.repositories.entities.TodoEntity;
import com.example.mytodolist.features.repositories.tododb.TodoDao;

import java.util.List;

import javax.inject.Inject;

public class TodoListRepositoryImpl implements TodoListRepository{

    @Inject
    TodoDao dao;

    @Inject
    public TodoListRepositoryImpl(){}

    @Override
    public List<TodoEntity> getTodoList() {
        return dao.getTodoList();
    }

    @Override
    public long saveTodo(TodoEntity todo) {
        return dao.saveTodo(todo);
    }

    @Override
    public void deleteTodo(TodoEntity todo) {
        dao.deleteTodo(todo);
    }

    @Override
    public int updateTodo(TodoEntity todo) {
        return dao.updateTodo(todo);
    }
}
