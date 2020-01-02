package com.eniola.capstoneproject_mynotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityMainBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase firebaseDatabase;
    SharedPreferenceBaseClass sharedPreferenceBaseClass;
    private String username, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        final int SPLASH_SCREEN_TIME_DURATION = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                finish();
            }
        }, SPLASH_SCREEN_TIME_DURATION);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    //user is signed in
                    username = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    Log.d(AppConstant.DEBUG_TAG, "USER EMAIL IS " + email);
                    Log.d(AppConstant.DEBUG_TAG, "USER name IS " + username);
                    Toast.makeText(MainActivity.this, "You are currently signed in ", Toast.LENGTH_LONG).show();

                    //save user details in shared preference
                    //sharedPreferenceBaseClass.editPreference(AppConstant.APP_MAIN_PREFERENCE, AppConstant.USER_EMAIL, true);


                } else {
                    // user is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))
                                    .build(),
                            AppConstant.RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener );
    }
}
