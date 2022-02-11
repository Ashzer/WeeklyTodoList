package com.example.mytodolist.features.repositories.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mytodolist.features.ui.home.Todo;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(tableName = "todolist")
public class TodoEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int color;
    private String title;
    private LocalDate date;
    private String content;

    public TodoEntity(long id, int color, String title, LocalDate date, String content) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Todo toTodo() {
        return new Todo(id, color, title, date, content);
    }

}
