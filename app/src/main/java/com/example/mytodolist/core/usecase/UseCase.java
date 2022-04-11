package com.example.mytodolist.core.usecase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class UseCase<T,Params> {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public T execute(Params params) throws ExecutionException, InterruptedException {
        Callable<T> callable = () -> run(params);
        Future<T> result = executorService.submit(callable);
        return result.get();
    }

    public abstract T run(Params params);

    public interface Params{}
}
