package com.eniola.capstoneproject_mynotes.ui;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityDashboardBinding;
import com.eniola.capstoneproject_mynotes.ui.fragments.NoteFragment;
import com.eniola.capstoneproject_mynotes.ui.fragments.HomeFragment;
import com.eniola.capstoneproject_mynotes.ui.fragments.SettingsFragment;
import com.eniola.capstoneproject_mynotes.ui.fragments.TaskFragment;
import com.eniola.capstoneproject_mynotes.ui.fragments.dummy.DummyContent;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener,
        TaskFragment.OnListFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener {

    ActivityDashboardBinding dashboardActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardActivityBinding = DataBindingUtil.setContentView(DashboardActivity.this, R.layout.activity_dashboard);
        getHomeFragment();

        //initialize bottomview navigation bar
        BottomNavigationView bottomNavigationView =  dashboardActivityBinding.bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.navigation_notes:
                getNoteFragment();
                Log.d(AppConstant.DEBUG_TAG, "This is the notes fragment");
                break;

            case R.id.navigation_tasks:
                getTaskFragment();
                Log.d(AppConstant.DEBUG_TAG, "This is the tasks fragment");
                break;

            case R.id.navigation_settings:
                getSettingsFragment();
                Log.d(AppConstant.DEBUG_TAG, "This is the settings fragment");
                break;

                default:
                    getHomeFragment();
                    Log.d(AppConstant.DEBUG_TAG, "This is the home fragment");
                    break;
        }

        return true;
    }

    private void getHomeFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = HomeFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_home);
        }
    }

    private void getNoteFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        NoteFragment noteFragment = NoteFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, noteFragment);
        fragmentTransaction.commit();
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_notes);
        }
    }

    private void getTaskFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        TaskFragment taskFragment = TaskFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, taskFragment);
        fragmentTransaction.commit();
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_tasks);
        }
    }

    private void getSettingsFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        SettingsFragment settingsFragment = SettingsFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, settingsFragment);
        fragmentTransaction.commit();
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_settings);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    /*public String getDeviceId(){
        TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
    }*/
}
