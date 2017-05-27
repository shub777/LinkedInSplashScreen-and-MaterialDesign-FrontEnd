package com.example.shubhambhama.ibnox;

import android.support.v4.app.Fragment;

/**
 * Created by SHUBHAMBHAMA on 18-03-2017.
 */

public class SignupActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SignupFragment();
    }
}
