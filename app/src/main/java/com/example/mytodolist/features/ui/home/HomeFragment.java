package com.example.mytodolist.features.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mytodolist.core.platform.BaseFragment;
import com.example.mytodolist.databinding.FragmentHomeBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment implements TodoDeleteCallback, TodoUpdateCallback {
    FragmentHomeBinding binding;
    HomeViewModel viewModel;
    TodoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getCurrentTodoList().observe(this, todos -> adapter.refreshItems(todos));
        viewModel.getCurrentResult().observe(this, aLong -> viewModel.getTodoList());
        viewModel.getUpdateResult().observe(this, nInteger -> viewModel.getTodoList());
        viewModel.getUserData().observe(this, dataClass -> {Log.d(this.getClass().toString(), dataClass.toString());
            System.out.println(Thread.currentThread().getName());});


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new TodoAdapter(this::deleteTodo, this::updateTodo);
        binding.homeTodosRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.homeTodosRv.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(binding.homeTodosRv);
        //adapter.refreshItems(todos);

        binding.homeTodosFab.setOnClickListener(view1 -> {
            AddDialog dialog = new AddDialog();
            dialog.show(requireActivity().getSupportFragmentManager(), new Const().ADD_DIALOG);
            dialog.saveTodo(todo -> viewModel.saveTodo(todo));
        });

        viewModel.getTodoList();
    }

    @Override
    public void deleteTodo(int position, Todo todo) {
        adapter.removeAt(position);
        viewModel.deleteTodo(todo);
    }

    @Override
    public void updateTodo(int position, Todo todo) {
        Bundle args = new Bundle();
        args.putParcelable("todo", todo);
        AddDialog dialog = new AddDialog();
        dialog.setArguments(args);
        dialog.show(requireActivity().getSupportFragmentManager(), new Const().UPDATE_DIALOG);
        dialog.saveTodo(newTodo -> {
            viewModel.updateTodo(newTodo);
            adapter.replaceAt(position, newTodo);
        });

        dialog.cancelTodo(id -> adapter.refreshByTodoId(id));
    }
}


