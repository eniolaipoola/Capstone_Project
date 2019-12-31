package com.eniola.capstoneproject_mynotes.free;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.R;
import com.eniola.capstoneproject_mynotes.databinding.FragmentHomeBinding;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View rootView = fragmentHomeBinding.getRoot();

        //instantiate Ads
        AdView adView = fragmentHomeBinding.adView;
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AppConstant.AdUnitId);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        return rootView;
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

}
