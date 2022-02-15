package com.example.mytodolist.core.calendar.utils;

import static com.example.mytodolist.features.repositories.tododb.LocalDateStringConverter.localDateToString;

import android.util.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalDateUtils {
    public static String getDateFullName(LocalDate date) {
        return  date.format(DateTimeFormatter.ofPattern("yy년 MM월 dd일 EEEE"));
    }

    public static LocalDate getDateFromFullName(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yy년 MM월 dd일 EEEE"));
        }catch (Exception e){
            return LocalDate.now();
        }
    }

    public static String getDayName(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public static String getDayShortName(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }

    public static String getMonthNumber(LocalDate date) {
        return String.valueOf(date.getMonthValue());
    }

    public static String getMonthName(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public static String getYearNumber(LocalDate date) {
        return String.valueOf(date.getYear());
    }

    public static String getYearShortNumber(LocalDate date) {
        return String.valueOf(date.getYear()).substring(2);
    }

    public static String getYearName(LocalDate date) {
        return date.getYear() + "년";
    }

    public static String getYearShortName(LocalDate date) {
        return String.valueOf(date.getYear()).substring(2) + "년";
    }

    public static String getDayInMonth(LocalDate date) {
        return String.valueOf(date.getDayOfMonth());
    }

    public static String getDayInMonthName(LocalDate date) {
        return date.getDayOfMonth() + "일";
    }


    public static String getWeekInYear(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return String.valueOf(date.get(weekFields.weekOfWeekBasedYear()));
    }


    public static List<LocalDate> getFutureDatesFrom(int count, LocalDate from) {
        List<LocalDate> futureDatesList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            futureDatesList.add(from.plusDays(i));
        }
        return futureDatesList;
    }

    public static List<LocalDate> getPastDatesFrom(int count, LocalDate from) {
        List<LocalDate> pastDatesList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            pastDatesList.add(0, from.minusDays(i));
        }
        return pastDatesList;
    }

    public static List<LocalDate> getDates(int pastDays, int futureDays, boolean includeCurrentDate) {
        LocalDate today = LocalDate.now();
        List<LocalDate> futureList = getFutureDatesFrom(futureDays, today);
        List<LocalDate> pastList = getPastDatesFrom(pastDays, today);
        List<LocalDate> result = new ArrayList<>();
        result.addAll(pastList);
        if (includeCurrentDate) result.add(today);
        result.addAll(futureList);
        Log.d("future_check__", result.toString());
        return result;
    }
}
