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
    private LocalDate start;
    private LocalDate deadline;
    private String content;


    public TodoEntity(long id, int color, String title, LocalDate start, LocalDate deadline, String content) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.start = start;
        this.content = content;
        this.deadline = deadline;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Todo toTodo() {
        return new Todo(id, color, title, start, deadline, content);
    }

}
