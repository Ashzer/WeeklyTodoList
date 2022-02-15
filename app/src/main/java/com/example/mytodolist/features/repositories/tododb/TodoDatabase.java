package com.example.mytodolist.features.repositories.tododb;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mytodolist.features.repositories.entities.TodoEntity;

@Database(entities = {TodoEntity.class} , version = 1, exportSchema = false)
@TypeConverters({LocalDateStringConverter.class})
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
