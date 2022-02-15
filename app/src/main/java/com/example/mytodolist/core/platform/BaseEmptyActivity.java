package com.example.mytodolist.core.platform;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytodolist.databinding.ActivityEmptyBinding;

import dagger.hilt.android.AndroidEntryPoint;


public abstract class BaseEmptyActivity extends BaseActivity{

    public ActivityEmptyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmptyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // setSupportActionBar(binding.emptyToolbar);

        //setAppbarTitle("is it?");
    }

    //void setAppbarTitle(String title){
//        getSupportActionBar().setTitle(title);
//    }
}
