package com.example.mytodolist.features.repositories.tododb;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import com.example.mytodolist.features.ui.home.Const;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateStringConverter {
    @TypeConverter
    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, Const.mainDateTimeFormat);
    }

    @TypeConverter
    public static String localDateToString(@NonNull LocalDate date) {
        return date.format(Const.mainDateTimeFormat);
    }
}
