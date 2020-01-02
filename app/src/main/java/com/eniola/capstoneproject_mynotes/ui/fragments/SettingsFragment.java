package com.eniola.capstoneproject_mynotes.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentSettingsBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment {

    OnFragmentInteractionListener mListener;
    FragmentSettingsBinding fragmentSettingsBinding;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth firebaseAuth;
    private String username, email;

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
        fragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        View rootView = fragmentSettingsBinding.getRoot();

        //fetch user details from shared preference

        /*firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(AppConstant.DEBUG_TAG, "it got here in settings");
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    //user is signed in
                    username = firebaseUser.getDisplayName();
                    email = firebaseUser.getEmail();
                    fragmentSettingsBinding.userEmail.setText(email);
                    fragmentSettingsBinding.username.setText(username);
                    Log.d(AppConstant.DEBUG_TAG, "USER EMAIL IS " + email);
                    Log.d(AppConstant.DEBUG_TAG, "USER name IS " + username);
                    Toast.makeText(getActivity(), "You are currently signed in ", Toast.LENGTH_LONG).show();
                }
            }
        };*/

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
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
