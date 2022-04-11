package com.example.mytodolist.features.ui.home;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.core.platform.BaseFragment;
import com.example.mytodolist.databinding.FragmentHomeBinding;
import com.example.mytodolist.features.calendar.ScrollSpeedLinearLayoutManager;
import com.example.mytodolist.features.calendar.SingleRowCalendarAdapter;
import com.example.mytodolist.features.calendar.utils.LocalDateUtils;
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
    String check = "";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getCurrentTodoList().observe(this, todos -> {
            adapter.refreshItems(todos, LocalDateUtils.getDateFromFullName(binding.calendarLo.calendarMonthTv.getText().toString()));
            todos.forEach((todo) ->
                    Log.d(this.getClass().toString(), "value = " + todo.toString())
            );
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int count = 20;
        List<LocalDate> init = LocalDateUtils.getDates(count, count, true);
        calendarAdapter = new SingleRowCalendarAdapter(init);

        LinearLayoutManager linearLayoutManager = new ScrollSpeedLinearLayoutManager(getContext(), 10);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.calendarLo.calendarBodyRv.setLayoutManager(linearLayoutManager);
        binding.calendarLo.calendarBodyRv.setAdapter(calendarAdapter);

        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.calendarLo.calendarBodyRv.getLayoutManager();
        Point size = new Point();
        requireActivity().getWindowManager().getDefaultDisplay().getRealSize(size);

        DisplayMetrics outMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int itemWidth = (int) (70 * outMetrics.density + 1f);
        int screenWidth = outMetrics.widthPixels;
        layoutManager.scrollToPositionWithOffset(calendarAdapter.indexOf(LocalDate.now()), (screenWidth - itemWidth) / 2);

        binding.calendarLo.calendarBodyRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible = layoutManager.findFirstVisibleItemPosition();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                LocalDate center = calendarAdapter.get(Math.round(((float) firstVisible + (float) lastVisible) / 2f));

                if (!check.equals(LocalDateUtils.getDateFullName(center))) {
                    check = LocalDateUtils.getDateFullName(center);
                    String date = LocalDateStringConverter.localDateToString(LocalDateUtils.getDateFromFullName(check));

//                    viewModel.getTodoPool().forEach((s, todo) ->
//                            Log.d(this.getClass().toString(), "key = " + s + " , value = " + todo.toString())
//                    );
                    viewModel.getTodoListADay(date);
                    //adapter.refreshItems();
                    Log.d(this.getClass().toString(), "" + calendarAdapter.getItemCount());

                    int count = 15;
                    if (firstVisible < count) {
                       binding.calendarLo.calendarBodyRv.post(() -> {
                            LocalDate from = calendarAdapter.get(0);
                            List<LocalDate> pastFrom = LocalDateUtils.getPastDatesFrom(count, from);
                            calendarAdapter.insertItemsHead(pastFrom);
                            calendarAdapter.deleteItemsTail(count);

                        });
                        //     Log.d(this.getClass().toString(), String.valueOf(calendarAdapter.getItemCount()));
                    }
                    if (calendarAdapter.getItemCount() - lastVisible <= count) {
                        binding.calendarLo.calendarBodyRv.post(() -> {
                            LocalDate from = calendarAdapter.get(calendarAdapter.getItemCount() - 1);
                            List<LocalDate> futureTo = LocalDateUtils.getFutureDatesFrom(count, from);
                            calendarAdapter.insertItemsTail(futureTo);
                            calendarAdapter.deleteItemsHead(count);

                        });
                        //     Log.d(this.getClass().toString(), String.valueOf(calendarAdapter.getItemCount()));
                    }

                }
                binding.calendarLo.calendarMonthTv.setText(LocalDateUtils.getDateFullName(center));
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
            dialog.show(requireActivity().getSupportFragmentManager(), Const.ADD_DIALOG);
            dialog.saveTodo(todo -> viewModel.saveTodo(todo));
        });

        viewModel.getTodoListADay(LocalDateUtils.getDateFullName(LocalDate.now()));

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
        dialog.show(requireActivity().getSupportFragmentManager(), Const.UPDATE_DIALOG);
        dialog.saveTodo(newTodo -> {
            viewModel.updateTodo(newTodo);
            adapter.replaceAt(position, newTodo);
        });

        dialog.cancelTodo(id -> adapter.refreshByTodoId(id));
    }
}