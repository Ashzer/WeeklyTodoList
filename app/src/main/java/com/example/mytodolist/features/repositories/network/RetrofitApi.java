package com.example.mytodolist.features.repositories.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {
    @GET("posts/{id}")
    Call<DataClass> getName(@Path("id") String id);
}
