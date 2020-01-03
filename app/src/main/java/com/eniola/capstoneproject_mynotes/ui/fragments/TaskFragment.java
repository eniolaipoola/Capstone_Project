package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentTaskBinding;
import com.eniola.capstoneproject_mynotes.ui.CreateTaskActivity;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;

public class TaskFragment extends Fragment {

    private int mColumnCount = 1;
    public TaskFragment() {}

    public static TaskFragment newInstance(){
        return new TaskFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTaskBinding fragmentTaskBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_task, container, false);
        View view = fragmentTaskBinding.getRoot();

        //fetch bundle details
        if(getArguments() != null){
            String taskDescription = getArguments().getString("taskDescription");
            Log.d(AppConstant.DEBUG_TAG, "task retrieved in task fragment is " + taskDescription);
        }

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }

        fragmentTaskBinding.createNewTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instantiate activity to create task
                Intent intent = new Intent(getActivity(), CreateTaskActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
