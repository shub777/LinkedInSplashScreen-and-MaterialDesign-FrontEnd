package com.example.shubhambhama.ibnox;

import android.support.v4.app.Fragment;

/**
 * Created by SHUB-PC on 24-03-2017.
 */

public class SettingActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new SettingFragment();
    }
}
