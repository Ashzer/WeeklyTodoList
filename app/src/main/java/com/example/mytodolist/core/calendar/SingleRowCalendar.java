package com.example.mytodolist.core.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.R;

public class SingleRowCalendar extends RecyclerView{

    Boolean multiSelection;
    Boolean deselection;
    Boolean longPress;
    int pastDaysCount;
    int futureDaysCount;
    Boolean includeCurrentDate;
    int initialPositionIndex;


    public SingleRowCalendar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SingleRowCalendar, 0, 0
        );

        try {
            pastDaysCount = obtainStyledAttributes.getInt(R.styleable.SingleRowCalendar_pastDaysCount, 0);
            futureDaysCount = obtainStyledAttributes.getInt(R.styleable.SingleRowCalendar_futureDaysCount, 30);
            includeCurrentDate = obtainStyledAttributes.getBoolean(R.styleable.SingleRowCalendar_includeCurrentDate, true);
            initialPositionIndex = obtainStyledAttributes.getInt(R.styleable.SingleRowCalendar_initialPositionIndex, pastDaysCount);
            multiSelection = obtainStyledAttributes.getBoolean(R.styleable.SingleRowCalendar_multiSelection, false);
            deselection = obtainStyledAttributes.getBoolean(R.styleable.SingleRowCalendar_deselection, true);
            longPress = obtainStyledAttributes.getBoolean(R.styleable.SingleRowCalendar_longPress, false);
        }finally {
            obtainStyledAttributes.recycle();
        }


    }
}
