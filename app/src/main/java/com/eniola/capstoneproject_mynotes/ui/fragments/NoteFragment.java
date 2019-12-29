package com.eniola.capstoneproject_mynotes.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentNoteBinding;
import com.eniola.capstoneproject_mynotes.ui.CreateNoteActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private FragmentNoteBinding fragmentNoteBinding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        View rootView = fragmentNoteBinding.getRoot();

        // create view for a new note
        fragmentNoteBinding.shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNoteActivity.class);
                startActivity(intent);
            }
        });

        // fetch data from user
        // save in firebase
        // fetched saved data and use to populate home (recyclerview gridviews)


        firebaseDatabase = FirebaseDatabase .getInstance();
        //

        return rootView;
    }

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

}
