package com.eniola.capstoneproject_mynotes.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eniola.capstoneproject_mynotes.databinding.ItemNoteBinding;
import com.eniola.capstoneproject_mynotes.models.Notes;
import com.eniola.capstoneproject_mynotes.models.OnNoteClickedListener;
import com.eniola.capstoneproject_mynotes.ui.CreateNoteActivity;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> implements OnNoteClickedListener {

    private List<Notes> allNotes;
    private OnNoteClickedListener onNoteClickedListener;
    Context mContext;

    public NotesAdapter(List<Notes> firebaseNotes, Context context){
        this.allNotes = firebaseNotes;
        this.onNoteClickedListener = this;
        this.mContext = context;
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
        holder.bindDataToView(notes, onNoteClickedListener);
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

        private void bindDataToView(final Notes notes, final OnNoteClickedListener noteClickedListener){
            String title = notes.getTitle();
            String date_created = notes.getDate_created();
            String content = notes.getContent();

            itemHomeNoteBinding.noteTitleTextView.setText(title);
            itemHomeNoteBinding.noteCreatedDateTextView.setText(date_created);
            itemHomeNoteBinding.noteContentTextView.setText(content);
            itemHomeNoteBinding.noteCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteClickedListener.onNoteClicked(notes, view);
                }
            });
        }
    }

    @Override
    public void onNoteClicked(Notes notes, View view) {
        //launch create Activity page and set details to current note details
        Intent intent = new Intent(mContext, CreateNoteActivity.class);
        intent.putExtra("Notes", notes);
        view.getContext().startActivity(intent);
    }
}
