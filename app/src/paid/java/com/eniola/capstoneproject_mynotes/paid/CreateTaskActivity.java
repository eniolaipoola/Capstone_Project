package com.eniola.capstoneproject_mynotes.paid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateTaskBinding;
import com.eniola.capstoneproject_mynotes.models.Tasks;
import com.eniola.capstoneproject_mynotes.ui.DashboardActivity;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTaskActivity extends AppCompatActivity {

    ActivityCreateTaskBinding activityCreateTaskBinding;
    String taskDescription;
    EditText taskDescriptionEditText;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    SharedPreferenceBaseClass sharedPreferenceBaseClass;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task);

        taskDescriptionEditText = activityCreateTaskBinding.enterTask;
        //attempt to save note data to fire-base database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        sharedPreferenceBaseClass = new SharedPreferenceBaseClass(this);

        activityCreateTaskBinding.doneCreatingTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDescription = taskDescriptionEditText.getText().toString();
                Log.d(AppConstant.DEBUG_TAG, "task entered is " + taskDescription);

                username = sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE).getString(AppConstant.USERNAME, "");
                Log.d(AppConstant.DEBUG_TAG, "username entered is " + username);
                saveNewTask(username, taskDescription, AppConstant.TASK_NEW);

                //Go back to home page
                Intent intent = new Intent(CreateTaskActivity.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(CreateTaskActivity.this, "Task is successfully created", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void saveNewTask(String username, String taskDescription, String status){
        Tasks tasks = new Tasks(username, taskDescription, status);
        mDatabaseReference.child("tasks").push().setValue(tasks).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(AppConstant.DEBUG_TAG, "The saving of data to firebase is successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(AppConstant.DEBUG_TAG, "The saving of data to firebase failed");
            }
        });
    }
}
