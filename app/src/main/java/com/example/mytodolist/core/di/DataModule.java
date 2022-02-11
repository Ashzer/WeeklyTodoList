package com.example.mytodolist.core.di;

import android.app.Application;

import androidx.room.Room;

import com.example.mytodolist.BuildConfig;
import com.example.mytodolist.features.repositories.TodoListRepository;
import com.example.mytodolist.features.repositories.TodoListRepositoryImpl;
import com.example.mytodolist.features.repositories.network.NetworkRepo;
import com.example.mytodolist.features.repositories.tododb.TodoDao;
import com.example.mytodolist.features.repositories.tododb.TodoDatabase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {

    @Provides
    @Singleton
    public static TodoDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, "todo.db")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);

        }
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static TodoDao provideTodoDao(TodoDatabase db) {
        return db.todoDao();
    }

    @Binds
    public abstract TodoListRepository bindTodoListRepository(TodoListRepositoryImpl impl);

    @Binds
    public abstract NetworkRepo bindNetworkRepo(NetworkRepo.Network impl);

}
