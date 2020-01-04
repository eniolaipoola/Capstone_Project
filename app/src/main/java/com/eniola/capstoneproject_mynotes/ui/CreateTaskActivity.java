package com.eniola.capstoneproject_mynotes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateTaskBinding;
import com.eniola.capstoneproject_mynotes.models.Tasks;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

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
                username = sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE).getString(AppConstant.USERNAME, "");
                if(taskDescription != null){
                    saveNewTask(username, taskDescription, AppConstant.TASK_NEW);
                }

                //Go back to home page
                Intent intent = new Intent(CreateTaskActivity.this, DashboardActivity.class);
                startActivity(intent);
                FancyToast.makeText(CreateTaskActivity.this, getResources().getString
                        (R.string.task_create_success), FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
            }
        });
    }

    public void saveNewTask(String username, String taskDescription, String status){
        Tasks tasks = new Tasks(username, taskDescription, status);
        mDatabaseReference.child(AppConstant.Tasks).push().setValue(tasks).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(AppConstant.DEBUG_TAG, getResources().getString(R.string.fireebase_create_success));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(AppConstant.DEBUG_TAG, getResources().getString(R.string.firebase_create_failed));
            }
        });
    }
}
