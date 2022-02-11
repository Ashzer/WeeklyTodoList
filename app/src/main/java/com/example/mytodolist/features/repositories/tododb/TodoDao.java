package com.example.mytodolist.features.repositories.tododb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mytodolist.features.repositories.entities.TodoEntity;

import java.util.List;


@Dao
public interface TodoDao {

    @Insert
    long saveTodo(TodoEntity todo);

    @Query("select * from todolist order by date desc ,id desc")
    List<TodoEntity> getTodoList();

    @Delete
    void deleteTodo(TodoEntity todo);

    @Update
    int updateTodo(TodoEntity todo);
}
