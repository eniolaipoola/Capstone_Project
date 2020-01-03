package com.eniola.capstoneproject_mynotes.models;

import android.content.Context;

import com.eniola.capstoneproject_mynotes.utilities.AppConstant;
import com.eniola.capstoneproject_mynotes.utilities.SharedPreferenceBaseClass;

/**
 * Copyright (c) 2020 Eniola Ipoola
 * All rights reserved
 * Created on 03-Jan-2020
 */
public class User {
    SharedPreferenceBaseClass sharedPreferenceBaseClass;

    public User(Context context){
        this.sharedPreferenceBaseClass = new SharedPreferenceBaseClass(context);
    }

    public String getUsername(){
        return sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE)
                .getString(AppConstant.USERNAME, "");
    }

    public String getEmail(){
        return sharedPreferenceBaseClass.loadPreference(AppConstant.APP_MAIN_PREFERENCE)
                .getString(AppConstant.USER_EMAIL, "");
    }
}
