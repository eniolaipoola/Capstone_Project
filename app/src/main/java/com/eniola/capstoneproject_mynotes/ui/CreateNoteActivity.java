package com.eniola.capstoneproject_mynotes.ui;

import android.os.Bundle;

import com.eniola.capstoneproject_mynotes.Notes;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateNoteBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import java.text.DateFormat;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    ActivityCreateNoteBinding activityCreateNoteBinding;
    String noteTitleEditText, noteContentEditText, noteCreatedDateTime;

    //Start using the database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_note);

        Toolbar toolbar = activityCreateNoteBinding.toolbar;
        setSupportActionBar(toolbar);

        //save current time and date as note creation time
        noteCreatedDateTime = DateFormat.getDateTimeInstance().format(new Date());
        activityCreateNoteBinding.createdDateTextView.setText(noteCreatedDateTime);

        //get note title and content
        noteTitleEditText = activityCreateNoteBinding.noteTitle.getText().toString();
        noteContentEditText = activityCreateNoteBinding.noteContentEditText.getText().toString();

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        FloatingActionButton fab = activityCreateNoteBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //save note data in firebase


                Log.d(AppConstant.DEBUG_TAG, "note title is " + noteTitleEditText);
                Log.d(AppConstant.DEBUG_TAG, "note content is " + noteContentEditText);
                Log.d(AppConstant.DEBUG_TAG, "note created date is " + noteCreatedDateTime);
                writeNewNote(noteTitleEditText, new  Date(noteCreatedDateTime), noteCreatedDateTime);

                //save received data in firebase

                //retrieve received data from firebase


            }
        });
    }

    private void writeNewNote(String title, Date date_created, String content){
        Notes notes = new Notes(title, date_created, content);
        mFirebaseDatabase.getReference().child("notes").setValue(notes);
    }

}
