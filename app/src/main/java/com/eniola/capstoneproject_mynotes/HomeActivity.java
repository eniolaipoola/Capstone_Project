package com.eniola.capstoneproject_mynotes;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.eniola.capstoneproject_mynotes.databinding.ActivityHomeBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    ActivityHomeBinding homeActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivityBinding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);

        //initialize bottomview navigation bar
        BottomNavigationView bottomNavigationView =  homeActivityBinding.bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                Log.d(AppConstant.DEBUG_TAG, "This is the home fragment");
        }

        return true;
    }
}
