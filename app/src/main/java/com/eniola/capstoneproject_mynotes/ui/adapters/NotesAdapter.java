package com.eniola.capstoneproject_mynotes.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eniola.capstoneproject_mynotes.databinding.ItemNoteBinding;
import com.eniola.capstoneproject_mynotes.models.Notes;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import java.util.List;

/**
 * Copyright (c) 2019 Eniola Ipoola
 * All rights reserved
 * Created on 30-Dec-2019
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Notes> allNotes;

    public NotesAdapter(List<Notes> firebaseNotes){
        this.allNotes = firebaseNotes;
        Log.d(AppConstant.DEBUG_TAG, "notes data in adapter is " + allNotes.size());
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemNoteBinding itemNoteBinding = ItemNoteBinding.inflate(layoutInflater, parent, false);
        return new NotesViewHolder(itemNoteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        final Notes notes = allNotes.get(position);
        holder.bindDataToView(notes);
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{

        private final ItemNoteBinding itemHomeNoteBinding;

        public NotesViewHolder(ItemNoteBinding homeNoteBinding){
            super(homeNoteBinding.getRoot());
            this.itemHomeNoteBinding = homeNoteBinding;
        }

        private void bindDataToView(final Notes notes){
            String title = notes.getTitle();
            String date_created = notes.getDate_created();
            String content = notes.getContent();

            Log.d(AppConstant.DEBUG_TAG, " title in binding data to view " + notes.getTitle());
            Log.d(AppConstant.DEBUG_TAG, " " + date_created);
            Log.d(AppConstant.DEBUG_TAG, " " + content);

            itemHomeNoteBinding.noteTitleTextView.setText(title);
            itemHomeNoteBinding.noteCreatedDateTextView.setText(date_created);
            itemHomeNoteBinding.noteContentTextView.setText(content);

        }

    }
}
