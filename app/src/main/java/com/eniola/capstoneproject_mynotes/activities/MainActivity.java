package com.eniola.capstoneproject_mynotes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        final int SPLASH_SCREEN_TIME_DURATION = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                finish();
            }
        }, SPLASH_SCREEN_TIME_DURATION);
    }
}
