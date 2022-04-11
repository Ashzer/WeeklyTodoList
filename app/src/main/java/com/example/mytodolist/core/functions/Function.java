package com.example.mytodolist.core.functions;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.mytodolist.R;

public class Function {
    public final static ColorStateList getTintList(Context context, int colorNum) {
        switch (colorNum) {
            case 0:
                return ContextCompat.getColorStateList(context, R.color.selection_1);
            case 1:
                return ContextCompat.getColorStateList(context, R.color.selection_2);
            case 2:
                return ContextCompat.getColorStateList(context, R.color.selection_3);
            case 3:
                return ContextCompat.getColorStateList(context, R.color.selection_4);
            case 4:
                return ContextCompat.getColorStateList(context, R.color.selection_5);
        }
        return ContextCompat.getColorStateList(context, R.color.selection_1);
    }

    public final static Drawable getDrawable(Context context, int id) {
        return ContextCompat.getDrawable(context, id);
    }
}
