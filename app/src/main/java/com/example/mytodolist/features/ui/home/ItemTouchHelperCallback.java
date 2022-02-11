package com.example.mytodolist.features.ui.home;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.R;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private IItemTouchHelper listener;

    public ItemTouchHelperCallback(IItemTouchHelper listener) {
        this.listener = listener;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag_flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(drag_flags, swipe_flags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(this.getClass().toString(), String.valueOf(direction));
        if (direction == ItemTouchHelper.START) {
            listener.onItemSwipeLeftModify(viewHolder.getAdapterPosition());
        } else {
            listener.onItemSwipeRightDelete(viewHolder.getAdapterPosition());
        }

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        Bitmap icon;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View itemView = viewHolder.itemView;
            float height = itemView.getHeight();
            float width = itemView.getWidth();
            Paint paint = new Paint();

            if (dX > 0) {
                paint.setColor(Color.parseColor("#FF222222"));
                //paint.setAlpha((int)dX/(int)width/3*100);
                //Log.d(this.getClass().toString(),"dX = " + dX + " , width = "+ width + " , result = " + (int)dX/(int)width/3*-10000);
                RectF background = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom());

                c.drawRoundRect(background, 40f, 40f, paint);
                icon = drawableToBitmap(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_menu_delete, null));
                RectF iconDst = new RectF(itemView.getLeft(), itemView.getTop() , itemView.getLeft() + height  , itemView.getBottom());
                c.drawBitmap(icon, null, iconDst, null);


            } else {
                paint.setColor(Color.parseColor("#FF222222"));
                //paint.setAlpha((int)dX/(int)width/3*-100);
                //Log.d(this.getClass().toString(),"dX = " + dX + " , width = "+ width + " , result = " + (int)dX/(int)width/3*10000);
                RectF background = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                c.drawRoundRect(background, 40f, 40f, paint);
                icon = drawableToBitmap(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_menu_modify, null));
                RectF iconDst = new RectF(itemView.getRight() - height, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                c.drawBitmap(icon, null, iconDst, null);

            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


}
