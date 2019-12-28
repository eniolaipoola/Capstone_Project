package com.eniola.capstoneproject_mynotes.fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentHomeBinding;
import com.eniola.capstoneproject_mynotes.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View rootView = fragmentHomeBinding.getRoot();

        return rootView;
    }


    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return  homeFragment;
    }
}
