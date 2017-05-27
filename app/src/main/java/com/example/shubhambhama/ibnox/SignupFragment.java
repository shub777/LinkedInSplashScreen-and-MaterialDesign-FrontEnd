package com.example.shubhambhama.ibnox;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupFragment extends Fragment implements View.OnClickListener {

    private EditText signupFullname;
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText signupConfirmPassword;
    private Button signupButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_signup,container,false);
        signupFullname=(EditText) v.findViewById(R.id.signup_fullname);
        signupEmail=(EditText) v.findViewById(R.id.signup_email);
        signupPassword=(EditText) v.findViewById(R.id.signup_password);
        signupConfirmPassword=(EditText) v.findViewById(R.id.signup_confirm_password);
        signupButton=(Button) v.findViewById(R.id.signup_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signupButton.getBackground().setAlpha(120);
        }
        else{
            signupPassword.setTypeface(Typeface.DEFAULT);
            signupConfirmPassword.setTypeface(Typeface.DEFAULT);
        }
        signupButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"Hey you just clicked signup Button",Toast.LENGTH_SHORT).show();
    }
}
