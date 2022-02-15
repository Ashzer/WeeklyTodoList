package com.example.mytodolist.features.ui.home;

import android.animation.ObjectAnimator;
import android.graphics.Point;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.core.calendar.SingleRowCalendarAdapter;
import com.example.mytodolist.core.calendar.utils.LocalDateUtils;
import com.example.mytodolist.core.platform.BaseFragment;
import com.example.mytodolist.databinding.FragmentHomeBinding;
import com.example.mytodolist.features.repositories.tododb.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment implements TodoDeleteCallback, TodoUpdateCallback {
    FragmentHomeBinding binding;
    HomeViewModel viewModel;
    TodoAdapter adapter;
    SingleRowCalendarAdapter calendarAdapter;

    Boolean isMenusFabOpen = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getCurrentTodoList().observe(this, todos -> {
//            if (todos.size() > 2) {
//                if (adapter.todos.contains(todos.get(1)) && (adapter.todos.size() + 1) == todos.size()) {
//                    adapter.insertAt(0, todos.get(0));
//                } else {
//                    adapter.refreshItems(todos);
//                }
//            } else {
            adapter.refreshItems(todos);
//            }
        });

        viewModel.getCurrentResult().observe(this, aLong -> {
            String date = LocalDateStringConverter.localDateToString(LocalDateUtils.getDateFromFullName(binding.calendarLo.calendarMonthTv.getText().toString()));
            viewModel.getTodoListADay(date);
        });
        viewModel.getUpdateResult().observe(this, nInteger -> {
            String date = LocalDateStringConverter.localDateToString(LocalDateUtils.getDateFromFullName(binding.calendarLo.calendarMonthTv.getText().toString()));
            viewModel.getTodoListADay(date);
        });
        viewModel.getUserData().observe(this, dataClass -> {
            Log.d(this.getClass().toString(), dataClass.toString());
            System.out.println(Thread.currentThread().getName());
        });


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
    String check = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<LocalDate> init = LocalDateUtils.getDates(10, 10, true);
        calendarAdapter = new SingleRowCalendarAdapter(init);
        binding.calendarLo.calendarBodyRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.calendarLo.calendarBodyRv.setAdapter(calendarAdapter);


        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.calendarLo.calendarBodyRv.getLayoutManager();
        Point size = new Point();
        requireActivity().getWindowManager().getDefaultDisplay().getRealSize(size);
        layoutManager.scrollToPositionWithOffset(calendarAdapter.indexOf(LocalDate.now()), (size.x) / 2 - 135);


        binding.calendarLo.calendarBodyRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible = layoutManager.findFirstCompletelyVisibleItemPosition();
                int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();


                LocalDate center = calendarAdapter.get(Math.round(((float) firstVisible + (float) lastVisible) / 2f));

                if(!check.equals(LocalDateUtils.getDateFullName(center))){
                    check = LocalDateUtils.getDateFullName(center);
                    String date = LocalDateStringConverter.localDateToString(LocalDateUtils.getDateFromFullName(check));
                    viewModel.getTodoListADay(date);
                }

                binding.calendarLo.calendarMonthTv.setText(LocalDateUtils.getDateFullName(center));

                if (firstVisible < 5) {
                    LocalDate from = calendarAdapter.get(0);
                    calendarAdapter.insertItemsHead(LocalDateUtils.getPastDatesFrom(20, from));
                }
                if (calendarAdapter.getItemCount() - lastVisible < 5) {
                    LocalDate from = calendarAdapter.get(calendarAdapter.getItemCount() - 1);
                    calendarAdapter.insertItemsTail(LocalDateUtils.getFutureDatesFrom(20, from));
                }
            }
        });


        adapter = new TodoAdapter(this::deleteTodo, this::updateTodo);
        binding.homeTodosRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.homeTodosRv.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(binding.homeTodosRv);
        //adapter.refreshItems(todos);

        binding.homeMenusFab.setOnClickListener(menuFab -> {
            if (isMenusFabOpen) {
                ObjectAnimator.ofFloat(binding.homeCreateFab, "translationY", 0f).start();
                ObjectAnimator.ofFloat(binding.homeSettingsFab, "translationY", 0f).start();
                binding.homeMenusFab.setImageResource(android.R.drawable.ic_input_add);
            } else {
                ObjectAnimator.ofFloat(binding.homeCreateFab, "translationY", -180f).start();
                ObjectAnimator.ofFloat(binding.homeSettingsFab, "translationY", -360f).start();
                binding.homeMenusFab.setImageResource(android.R.drawable.ic_delete);


                LocalDateUtils.getDates(5, 4, true);
                Log.d(this.getClass().toString(), String.valueOf(LocalDate.now().getDayOfYear()));

            }
            isMenusFabOpen = !isMenusFabOpen;
        });


        binding.homeCreateFab.setOnClickListener(createFab -> {
            AddDialog dialog = new AddDialog();
            dialog.show(requireActivity().getSupportFragmentManager(), new Const().ADD_DIALOG);
            dialog.saveTodo(todo -> viewModel.saveTodo(todo));
        });

        viewModel.getTodoListADay(LocalDateStringConverter.localDateToString(LocalDate.now()));

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