package com.eniola.capstoneproject_mynotes.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentNoteBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private FragmentNoteBinding fragmentNoteBinding;


    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentNoteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        View rootView = fragmentNoteBinding.getRoot();

        return rootView;
    }

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

}
