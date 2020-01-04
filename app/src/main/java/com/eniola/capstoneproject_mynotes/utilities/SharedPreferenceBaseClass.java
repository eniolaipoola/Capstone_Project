package com.eniola.capstoneproject_mynotes.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceBaseClass {

    public Context mContext;

    public SharedPreferenceBaseClass(Context context) {
        this.mContext = context;
    }

    public SharedPreferences loadPreference(String prefName) {
        return mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public void editPreference(String prefName, String key, String value) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

}
