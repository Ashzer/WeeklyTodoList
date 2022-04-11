package com.example.mytodolist.features.calendar;

import java.time.LocalDate;
import java.util.List;

public class Day {
    LocalDate date;
    List<Integer> colors;

    public Day(LocalDate date, List<Integer> colors){
        this.date = date;
        this.colors = colors;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getColors() {
        return colors;
    }

    public void setColors(List<Integer> colors) {
        this.colors = colors;
    }
}
