package com.example.mytodolist.features.calendar;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.gridlayout.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.R;
import com.example.mytodolist.features.calendar.utils.LocalDateUtils;
import com.example.mytodolist.core.functions.Function;
import com.example.mytodolist.databinding.CalendarDayItemBinding;

import java.time.LocalDate;
import java.util.List;

public class SingleRowCalendarAdapter extends RecyclerView.Adapter<SingleRowCalendarAdapter.ViewHolder> {
    List<LocalDate> dates;
    public SingleRowCalendarAdapter(List<LocalDate> dates ) {
        this.dates = dates;
        notifyItemRangeInserted(0,dates.size());
    }

    public void insertItemsHead(List<LocalDate> dates) {
        this.dates.addAll(0, dates);
        notifyItemRangeInserted(0, dates.size());
    }

    public void deleteItemsHead(int count){
        this.dates.removeIf(localDate -> dates.indexOf(localDate)<count);
        notifyItemRangeRemoved(0,count);

    }

    public void insertItemsTail(List<LocalDate> dates) {
        int oldSize = this.dates.size();
        this.dates.addAll(oldSize, dates);
        notifyItemRangeInserted(oldSize, dates.size());
    }

    public void deleteItemsTail(int count){
        int size = this.dates.size();
        this.dates.removeIf(localDate -> dates.indexOf(localDate) > size-count-1);
        notifyItemRangeRemoved(this.dates.size(),count);
    }

    public LocalDate get(int i) {
        return dates.get(i);
    }

    public int indexOf(LocalDate date) {
        return dates.indexOf(date);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_day_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dates.get(position));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CalendarDayItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = CalendarDayItemBinding.bind(itemView);
        }

        void bind(LocalDate date) {
            //binding.calendarDateTv.setText(LocalDateUtils.getDateFullName(date));
            binding.calendarDateTv.setText(LocalDateUtils.getDayInMonth(date));
            binding.calenderDayOfWeekTv.setText(LocalDateUtils.getDayShortName(date));
        }

    }
}
