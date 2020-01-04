package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentSettingsBinding;
import com.eniola.capstoneproject_mynotes.models.User;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {}

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSettingsBinding fragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        View rootView = fragmentSettingsBinding.getRoot();

        //fetch user details from shared preference
        User user = new User(getActivity());
        fragmentSettingsBinding.username.setText(user.getUsername());
        fragmentSettingsBinding.userEmail.setText(user.getEmail());

        //populate all spinners in settings page
        Spinner font_spinner = fragmentSettingsBinding.fontStyleSpinner;
        ArrayAdapter<String> fontArrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.font_style_array));
        fontArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        font_spinner.setAdapter(fontArrayAdapter);

        font_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner sort_note_spinner = fragmentSettingsBinding.noteDateCreatedSpinner;
        ArrayAdapter<String> sortNotesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sort_note_array));
        sortNotesAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sort_note_spinner.setAdapter(sortNotesAdapter);

        sort_note_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner sort_task_spinner = fragmentSettingsBinding.taskDateCreatedSpinner;
        ArrayAdapter<String> sortTaskAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sort_note_array));
        sortTaskAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sort_task_spinner.setAdapter(sortTaskAdapter);

        sort_task_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
