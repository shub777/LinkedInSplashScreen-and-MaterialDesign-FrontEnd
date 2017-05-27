package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SHUBHAMBHAMA on 06-03-2017.
 */

public class SavedPreferenceFile {
    private static final String FIRST_LAUNCH = "activity_first_launch";
    private static String ISFIRSTLAUNCH;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public SavedPreferenceFile(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FIRST_LAUNCH, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setFirst(String ISFIRSTLAUNCH,boolean isFirst) {
        this.ISFIRSTLAUNCH=ISFIRSTLAUNCH;
        editor.putBoolean(ISFIRSTLAUNCH, isFirst);
        editor.commit();
    }

    public boolean checkIsFirst(String ISFIRSTLAUNCH)  {
        this.ISFIRSTLAUNCH=ISFIRSTLAUNCH;
        return preferences.getBoolean(ISFIRSTLAUNCH, true);
    }

}
