package com.example.mytodolist.features.repositories.network;

import java.io.IOException;

import javax.inject.Inject;

public interface NetworkRepo {
    DataClass getName(String id);

    class Network implements NetworkRepo {
        @Inject
        RetrofitService retrofitService;
        @Inject
        Network(){}

        @Override
        public DataClass getName(String id) {
            try {
                return retrofitService.getName(id).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new DataClass();
        }
    }
}
