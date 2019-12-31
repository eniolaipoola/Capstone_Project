package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentTaskListBinding;
import com.eniola.capstoneproject_mynotes.ui.fragments.dummy.DummyContent;
import com.eniola.capstoneproject_mynotes.ui.fragments.dummy.DummyContent.DummyItem;

public class TaskFragment extends Fragment {

    private int mColumnCount = 1;
    public OnListFragmentInteractionListener mListener;
    public FragmentTaskListBinding taskListBinding;

    public TaskFragment() {}

    public static TaskFragment newInstance(){
        return new TaskFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        taskListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_list, container, false);
        View view = taskListBinding.getRoot();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyTaskRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
