package com.example.mytodolist.features.repositories.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Retrofit;

@Singleton
public class RetrofitService implements RetrofitApi{
    private RetrofitApi retrofitApi;
    final Retrofit retrofit;
    @Inject
    RetrofitService(Retrofit retrofit){
        this.retrofit = retrofit;
        retrofitApi = this.retrofit.create(RetrofitApi.class);
    }

    @Override
    public Call<DataClass> getName(String id) {
        return retrofitApi.getName(id);
    }
}
