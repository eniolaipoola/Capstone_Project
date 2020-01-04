package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentTaskBinding;
import com.eniola.capstoneproject_mynotes.free.CreateTaskActivity;
import com.eniola.capstoneproject_mynotes.models.Tasks;
import com.eniola.capstoneproject_mynotes.ui.adapters.TaskAdapter;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class TaskFragment extends Fragment {

    Tasks tasks;
    private ArrayList<Tasks> tasksFromFirebase;
    private TaskAdapter taskAdapter;

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
        final FragmentTaskBinding fragmentTaskBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_task, container, false);
        View view = fragmentTaskBinding.getRoot();

        //fetch bundle details
        if(getArguments() != null){
            String taskDescription = getArguments().getString("taskDescription");
            Log.d(AppConstant.DEBUG_TAG, "task retrieved in task fragment is " + taskDescription);
        }

        //retrieve users specific saved notes, if any
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference().child("tasks");
        tasksFromFirebase = new ArrayList<>();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    tasks = snapshot.getValue(Tasks.class);
                    if(tasks != null){
                        tasksFromFirebase.add(tasks);
                        taskAdapter = new TaskAdapter(tasksFromFirebase);
                        taskAdapter.notifyDataSetChanged();
                        //add data to recycler-view adapter
                        fragmentTaskBinding.homeTaskRecyclerView.setAdapter(taskAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(AppConstant.DEBUG_TAG, "An error occurred when trying to fetch data from firebase " + tasksFromFirebase.size());
            }
        });

        //bind recycler-view to view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragmentTaskBinding.homeTaskRecyclerView.setLayoutManager(linearLayoutManager);
        fragmentTaskBinding.homeTaskRecyclerView.setHasFixedSize(true);

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
