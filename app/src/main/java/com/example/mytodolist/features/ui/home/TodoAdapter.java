package com.example.mytodolist.features.ui.home;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.R;
import com.example.mytodolist.databinding.ItemTodoBinding;
import com.example.mytodolist.features.repositories.tododb.LocalDateStringConverter;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> implements IItemTouchHelper {

    public TodoDeleteCallback deleteCallback;
    public TodoUpdateCallback updateCallback;
    List<Todo> todos = new ArrayList<>();

    public TodoAdapter(TodoDeleteCallback deleteCallback, TodoUpdateCallback updateCallback) {
        this.deleteCallback = deleteCallback;
        this.updateCallback = updateCallback;
    }

    public void refreshItems(List<Todo> todos) {
        this.todos.clear();
        this.todos = todos;
        notifyDataSetChanged();
        //notifyItemRangeChanged(0,todos.size());
    }

    public void insertAt(int position, Todo todo) {
        this.todos.add(position, todo);
        notifyItemInserted(position);
    }

    public void refreshByTodoId(long id) {

        for (int i = 0; i < getItemCount(); i++) {
            if (this.todos.get(i).getId() == id) {
                notifyItemChanged(i);
            }
        }
    }

    public void replaceAt(int position, Todo todo) {
        todos.set(position, todo);
    }

    public void removeAt(int position) {
        todos.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(todos.get(position));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        return false;
    }

    @Override
    public void onItemSwipeLeftModify(int position) {
        updateCallback.updateTodo(position, todos.get(position));
    }

    @Override
    public void onItemSwipeRightDelete(int position) {
        deleteCallback.deleteTodo(position, todos.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemTodoBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemTodoBinding.bind(itemView);
        }

        void bind(Todo todo) {
            binding.itemTodoTitleTv.setText(todo.getTitle());
            binding.itemTodoDateTv.setText(LocalDateStringConverter.localDateToString(todo.getStart()));
            binding.itemTodoContentEt.setText(todo.getContent());
            binding.itemTodoDeadlineTv.setText(LocalDateStringConverter.localDateToString(todo.getDeadline()));
            binding.getRoot().getBackground().setColorFilter(todo.getColor(), PorterDuff.Mode.SRC_IN);
        }
    }

}
