package com.eniola.capstoneproject_mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateTaskBinding;
import com.eniola.capstoneproject_mynotes.ui.fragments.TaskFragment;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;

public class CreateTaskActivity extends AppCompatActivity {
    ActivityCreateTaskBinding activityCreateTaskBinding;
    String taskDescription;
    EditText taskDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateTaskBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task);

        taskDescriptionEditText = activityCreateTaskBinding.enterTask;
        activityCreateTaskBinding.doneCreatingTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskDescription = taskDescriptionEditText.getText().toString();
                Log.d(AppConstant.DEBUG_TAG, "task entered is " + taskDescription);

                //pass data from activity to fragment
                Bundle bundle = new Bundle();
                bundle.putString("taskDescription", taskDescription);
                TaskFragment taskFragment = new TaskFragment();
                taskFragment.setArguments(bundle);


            }
        });
    }
}
