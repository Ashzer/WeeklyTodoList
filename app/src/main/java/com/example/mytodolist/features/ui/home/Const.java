package com.example.mytodolist.features.ui.home;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.mytodolist.R;

import java.time.format.DateTimeFormatter;

public class Const {
    public final static String ADD_DIALOG = "AddTodo.Dialog";
    public final static String UPDATE_DIALOG = "UpdateTodo.Dialog";
    public final static DateTimeFormatter mainDateTimeFormat = DateTimeFormatter.ofPattern("yy년 MM월 dd일 EEEE");
}
