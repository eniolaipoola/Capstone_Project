package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
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
import com.eniola.capstoneproject_mynotes.models.User;
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

    private NotesAdapter notesAdapter;
    private ArrayList<Notes> notesFromFirebase;
    private FragmentNoteBinding fragmentNoteBinding;
    private Notes notes;

    public NoteFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        View rootView = fragmentNoteBinding.getRoot();

        notesFromFirebase = new ArrayList<>();
        User user = new User(getActivity());

        //retrieve users specific saved notes, if any
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference().child(AppConstant.NOTES).child(user.getUsername());
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    notes = snapshot.getValue(Notes.class);
                    String noteId = dataSnapshot.getKey();
                    notes.setId(noteId);
                    if(notes != null){
                        notesFromFirebase.add(notes);
                        notesAdapter = new NotesAdapter(notesFromFirebase, getActivity());
                        notesAdapter.notifyDataSetChanged();
                        //add data to recycler-view adapter
                        fragmentNoteBinding.homeNoteRecyclerView.setAdapter(notesAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(AppConstant.DEBUG_TAG, getResources().getString(R.string.firebase_create_failed) + notesFromFirebase.size());
            }
        });

        //bind recycler-view to view
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                (calculateNumberOfColumns(getActivity()), StaggeredGridLayoutManager.VERTICAL);
        fragmentNoteBinding.homeNoteRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        fragmentNoteBinding.homeNoteRecyclerView.setHasFixedSize(true);

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

    private static int calculateNumberOfColumns(Context context){
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
