package com.example.mytodolist.core.platform;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mytodolist.features.ui.home.AddDialog;
import com.example.mytodolist.features.ui.home.HomeFragment;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    protected void addFragment(int containerViewId, Fragment fragment){
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }


    private long backKeyPressedTime = 0;
    private long terminationThreshold = 2000; //millisecond
    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        for(Fragment fragment : fragments){
            if(fragment.isVisible()){
                Log.d(this.getClass().toString() , fragment.getClass().toString());
                if(fragment instanceof HomeFragment){
                    if(System.currentTimeMillis() > backKeyPressedTime + terminationThreshold){
                        backKeyPressedTime =  System.currentTimeMillis();
                        Toast.makeText(this, "한번 더 백키를 누르시면 어플이 종료됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(System.currentTimeMillis() <= backKeyPressedTime + terminationThreshold){
                        finish();
                    }
                }else if(fragment instanceof AddDialog){
                    Log.d(this.getClass().toString() , "You got it right?");
                }

            }
        }
    }

}
