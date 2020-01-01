package com.eniola.capstoneproject_mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateTaskBinding;

public class CreateTaskActivity extends AppCompatActivity {

    ActivityCreateTaskBinding activityCreateTaskBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task);
        if(getActionBar() != null){
            getActionBar().setTitle(R.string.task_title);
        }


    }
}
