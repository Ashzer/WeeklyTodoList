package com.example.mytodolist.features.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.example.mytodolist.core.platform.BaseEmptyActivity;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseEmptyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View content = binding.emptyFragmentContainer;
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        return true;
                    }
                }
        );

        addFragment(binding.emptyFragmentContainer.getId(), new HomeFragment());
    }
}