package com.eniola.capstoneproject_mynotes.ui;

import android.os.Bundle;

import com.eniola.capstoneproject_mynotes.Notes;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateNoteBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import java.text.DateFormat;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    ActivityCreateNoteBinding activityCreateNoteBinding;
    String noteTitle;
    String noteContent;
    String noteCreatedDateTime;

    //Start using the database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_note);
        Toolbar toolbar = activityCreateNoteBinding.toolbar;
        setSupportActionBar(toolbar);

        //get current time and date and display as note creation time
        noteCreatedDateTime = DateFormat.getDateTimeInstance().format(new Date());
        activityCreateNoteBinding.createdDateTextView.setText(noteCreatedDateTime);


        FloatingActionButton fab = activityCreateNoteBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get note title and content
                noteTitle = activityCreateNoteBinding.noteTitle.getText().toString();
                noteContent = activityCreateNoteBinding.noteContentEditText.getText().toString();
                Log.d(AppConstant.DEBUG_TAG, "note title is " + noteTitle);
                Log.d(AppConstant.DEBUG_TAG, "note content is " + noteContent);
                Log.d(AppConstant.DEBUG_TAG, "note created date is " + noteCreatedDateTime);

                //fire up firebase instance
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                mDatabaseReference = mFirebaseDatabase.getReference().child("notes");

                //attempt to save note data to firebase database
                writeNewNote(noteTitle, noteCreatedDateTime, noteContent);

                //save received data in firebase

                //retrieve received data from firebase


            }
        });
    }

    private void writeNewNote(String title, String date_created, String content){
        Notes notes = new Notes(title, date_created, content);
        mDatabaseReference.child("notes").setValue(notes).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(AppConstant.DEBUG_TAG, "The saving of data to firebase is successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(AppConstant.DEBUG_TAG, "The saving of data to firebase failed");
            }
        });
    }

}
