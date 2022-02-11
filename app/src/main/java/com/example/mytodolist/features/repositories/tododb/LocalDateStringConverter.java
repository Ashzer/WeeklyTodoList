package com.example.mytodolist.features.repositories.tododb;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverter {
    @TypeConverter
    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yy년 MM월 dd일 (E)"));
    }

    @TypeConverter
    public static String localDateToString(@NonNull LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 (E)"));
    }
}
