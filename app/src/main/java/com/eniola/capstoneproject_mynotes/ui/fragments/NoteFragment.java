package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.models.Notes;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentNoteBinding;
import com.eniola.capstoneproject_mynotes.ui.CreateNoteActivity;
import com.eniola.capstoneproject_mynotes.ui.adapters.NotesAdapter;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class NoteFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private NotesAdapter notesAdapter;
    private ArrayList<Notes> notesFromFirebase;
    private FragmentNoteBinding fragmentNoteBinding;
    private Notes notes;

    public NoteFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        View rootView = fragmentNoteBinding.getRoot();

        notesFromFirebase = new ArrayList<>();

        //bind recyclerview to view
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(calculateNumberOfColumns(getActivity()), StaggeredGridLayoutManager.VERTICAL);
        fragmentNoteBinding.homeNoteRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        fragmentNoteBinding.homeNoteRecyclerView.setHasFixedSize(true);

        //retrieve saved notes, if any
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("notes");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    notes = snapshot.getValue(Notes.class);
                    notesFromFirebase.add(notes);
                }

                notesAdapter = new NotesAdapter(notesFromFirebase);
                notesAdapter.notifyDataSetChanged();

                //add data to recyclerview adapter
                fragmentNoteBinding.homeNoteRecyclerView.setAdapter(notesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(AppConstant.DEBUG_TAG, "An error occurred when trying to fetch data from firebase " + notesFromFirebase.size());
            }
        });

        // create view for a new note
        fragmentNoteBinding.shareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateNoteActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    public static int calculateNumberOfColumns(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);

        if(noOfColumns < 2){
            noOfColumns = 2;
        }
        return  noOfColumns;
    }
}
