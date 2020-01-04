package com.eniola.capstoneproject_mynotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;
import butterknife.ButterKnife;


/**
 * Use butterKnife to bind data to view rather than databinding here
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase firebaseDatabase;
    SharedPreferenceBaseClass sharedPreferenceBaseClass;
    private String username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferenceBaseClass = new SharedPreferenceBaseClass(this);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    //user is signed in
                    username = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    Toast.makeText(MainActivity.this,  getResources().getString(R.string.firebase_signin_status), Toast.LENGTH_SHORT).show();

                    //save user details in shared preference
                    sharedPreferenceBaseClass.editPreference(AppConstant.APP_MAIN_PREFERENCE, AppConstant.USER_EMAIL, email);
                    sharedPreferenceBaseClass.editPreference(AppConstant.APP_MAIN_PREFERENCE, AppConstant.USERNAME, username);


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

        final int SPLASH_SCREEN_TIME_DURATION = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                finish();
            }
        }, SPLASH_SCREEN_TIME_DURATION);

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
