package com.eniola.capstoneproject_mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.eniola.capstoneproject_mynotes.models.Notes;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.ActivityCreateNoteBinding;
import com.eniola.capstoneproject_mynotes.ui.fragments.NoteFragment;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    ActivityCreateNoteBinding activityCreateNoteBinding;
    String noteTitle;
    String noteContent;
    String noteCreatedDateTime;
    SharedPreferenceBaseClass sharedPreferenceBaseClass;
    FloatingActionButton floatingActionButton;
    Notes notes;

    //Start using the database
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateNoteBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_note);
        Toolbar toolbar = activityCreateNoteBinding.toolbar;
        setSupportActionBar(toolbar);

        floatingActionButton = activityCreateNoteBinding.fab;
        sharedPreferenceBaseClass = new SharedPreferenceBaseClass(this);
        final String username = sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE).getString(AppConstant.USERNAME, "");
        //fire up firebase instance
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        Intent intent = getIntent();
        if(intent != null){
            notes = (Notes) intent.getSerializableExtra("Notes");
            if(notes != null){
                //display current note for editing
                activityCreateNoteBinding.noteTitle.setText(notes.getTitle());
                activityCreateNoteBinding.noteContentEditText.setText(notes.getContent());
                activityCreateNoteBinding.createdDateTextView.setText(notes.getDate_created());

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //update note details
                        updateCurrentNote(username, notes.getTitle(), notes.getContent(), notes.getDate_created());
                        //Go back to note fragment page
                        getNoteFragment();
                    }
                });
            }
        }

        noteCreatedDateTime = DateFormat.getDateTimeInstance().format(new Date());
        activityCreateNoteBinding.createdDateTextView.setText(noteCreatedDateTime);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get note title and content
                noteTitle = activityCreateNoteBinding.noteTitle.getText().toString();
                noteContent = activityCreateNoteBinding.noteContentEditText.getText().toString();

                //attempt to save note data to firebase database
                writeNewNote(username, noteTitle, noteCreatedDateTime, noteContent);

                //Go back to home page
                Intent intent = new Intent(CreateNoteActivity.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(CreateNoteActivity.this, "Note is successfully created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeNewNote(String username, String title, String content, String dateCreated){
        Notes notes = new Notes(username, title, dateCreated, content);
        mDatabaseReference.child("notes").child(username).push().setValue(notes).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void updateCurrentNote(String username, String title, String noteContent, final String dateCreated){
        mDatabaseReference = mFirebaseDatabase.getReference("notes");
        String key = mDatabaseReference.child("notes").child(username).push().getKey();
        if(key != null){
            mDatabaseReference.child("notes").child(username).child(key).child("title").setValue(title);
            mDatabaseReference.child("notes").child(username).child(key).child("content").setValue(noteContent);
            mDatabaseReference.child("notes").child(username).child(key).child("date_created").setValue(dateCreated);
        }
    }

    private void getNoteFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        NoteFragment noteFragment = NoteFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, noteFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case android.R.id.home:
                onBackPressed();
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
