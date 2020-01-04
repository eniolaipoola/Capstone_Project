package com.eniola.capstoneproject_mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.eniola.capstoneproject_mynotes.MyNotesWidgetProvider;
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
import com.shashank.sony.fancytoastlib.FancyToast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
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
    String username;

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
        username = sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE).getString(AppConstant.USERNAME, "");
        //fire up firebase instance
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        Intent intent = getIntent();
        if(intent != null){
            notes = (Notes) intent.getSerializableExtra(AppConstant.NOTES_SERIALIZABLE);
            if(notes != null){
                //display current note for editing
                activityCreateNoteBinding.noteTitle.setText(notes.getTitle());
                activityCreateNoteBinding.noteContentEditText.setText(notes.getContent());
                activityCreateNoteBinding.createdDateTextView.setText(notes.getDate_created());

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //update note details
                        updateCurrentNote(notes);
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

                new MyAsyncTask().execute();

                //Go back to home page
                Intent intent = new Intent(CreateNoteActivity.this, DashboardActivity.class);
                startActivity(intent);
                FancyToast.makeText(CreateNoteActivity.this, getResources().getString(R.string.note_create_success),
                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            writeNewNote();
            return null;
        }
    }

    private void writeNewNote(){
        Notes notes = new Notes(username, noteTitle, noteCreatedDateTime, noteContent);
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, MyNotesWidgetProvider.class));

        //show newly created note in widget
        MyNotesWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetIds, notes);

        mDatabaseReference.child(AppConstant.NOTES).child(username).push().setValue(notes).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(AppConstant.DEBUG_TAG, getResources().getString(R.string.fireebase_create_success));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(AppConstant.DEBUG_TAG, getResources().getString(R.string.firebase_create_failed));
            }
        });
    }

    private void updateCurrentNote(Notes notes){
        String currentNoteId = notes.getId();
        if(currentNoteId != null){
            mDatabaseReference.child(AppConstant.NOTES).child(username).setValue(notes);
        }
    }

    private void getNoteFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        NoteFragment noteFragment = NoteFragment.newInstance();
        fragmentTransaction.replace(R.id.fragment_container, noteFragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
