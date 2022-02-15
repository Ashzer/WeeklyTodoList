package com.example.mytodolist.features.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mytodolist.features.repositories.entities.TodoEntity;

import java.time.LocalDate;

import javax.inject.Inject;

public class Todo implements Parcelable {
    private long id;
    private int color;
    private String title;
    private LocalDate start;
    private LocalDate deadline;
    private String content;

    @Inject
    public Todo(long id, int color, String title, LocalDate start,LocalDate deadline, String content) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.start = start;
        this.deadline = deadline;
        this.content = content;
    }


    protected Todo(Parcel in) {
        id = in.readLong();
        color = in.readInt();
        title = in.readString();
        start = in.readParcelable(LocalDate.class.getClassLoader());
        deadline = in.readParcelable(LocalDate.class.getClassLoader());
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(color);
        dest.writeString(title);
        dest.writeValue(start);
        dest.writeValue(deadline);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public long getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStart() {
        return start;
    }
    public LocalDate getDeadline() {
        return deadline;
    }


    public String getContent() {
        return content;
    }

    public TodoEntity toTodoEntity() {
        return new TodoEntity(id, color, title, start,deadline, content);
    }

}
