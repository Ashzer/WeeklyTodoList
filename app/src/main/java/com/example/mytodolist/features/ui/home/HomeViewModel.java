package com.example.mytodolist.features.ui.home;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mytodolist.core.platform.BaseViewModel;
import com.example.mytodolist.features.repositories.network.DataClass;
import com.example.mytodolist.features.repositories.network.GetNameUseCase;
import com.example.mytodolist.features.repositories.tododb.usecases.DeleteTodoUseCase;
import com.example.mytodolist.features.repositories.tododb.usecases.GetTodoListUseCase;
import com.example.mytodolist.features.repositories.tododb.usecases.SaveTodoUseCase;
import com.example.mytodolist.features.repositories.tododb.usecases.UpdateTodoUseCase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {
    @Inject
    GetTodoListUseCase getTodoList;
    @Inject
    SaveTodoUseCase saveTodo;
    @Inject
    DeleteTodoUseCase deleteTodo;
    @Inject
    UpdateTodoUseCase updateTodo;

    @Inject
    GetNameUseCase getName;

    MutableLiveData<List<Todo>> todos = new MutableLiveData<>();
    MutableLiveData<Long> saveResult = new MutableLiveData<>();
    MutableLiveData<Integer> updateResult = new MutableLiveData<>();
    MutableLiveData<DataClass> userData = new MutableLiveData<>();


    @Inject
    public HomeViewModel() {
    }

    public void saveTodo(Todo todo) {
        saveResult.setValue(saveTodo.execute(todo));
        Log.d(this.getClass().toString(), saveResult.getValue().toString());

        CompletableFuture.runAsync(() -> userData.postValue(getName.execute("1")));
    }

    public void getTodoList() {
        todos.postValue(getTodoList.execute());
    }

    public void deleteTodo(Todo todo) {
        deleteTodo.execute(todo);
    }

    public void updateTodo(Todo todo) {
        updateResult.setValue(updateTodo.execute(todo));
    }

    public MutableLiveData<List<Todo>> getCurrentTodoList() {
        return todos;
    }

    public MutableLiveData<Long> getCurrentResult() {
        return saveResult;
    }

    public MutableLiveData<Integer> getUpdateResult() {
        return updateResult;
    }

    public MutableLiveData<DataClass> getUserData() {
        return userData;
    }
}
