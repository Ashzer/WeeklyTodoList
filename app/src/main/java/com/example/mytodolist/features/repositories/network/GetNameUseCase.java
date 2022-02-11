package com.example.mytodolist.features.repositories.network;

import javax.inject.Inject;

public class GetNameUseCase {
    @Inject
    NetworkRepo repository;
    @Inject
    GetNameUseCase(){}

    public DataClass execute(String id){
        return repository.getName(id);
    }
}
