package com.example.shubhambhama.ibnox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginFragment extends Fragment implements View.OnClickListener{
    private Button signinButton;
    private SavedPreferenceFile introSlider;
    private static int flag=0;
    private EditText signinEmail;
    private EditText signinPassword;
    private static final String ISFIRSTLAUNCH = "isFirstLaunch";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introSlider=new SavedPreferenceFile(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login,container,false);
        signinButton=(Button) v.findViewById(R.id.signin_button);
        signinEmail=(EditText) v.findViewById(R.id.signin_email);
        signinPassword=(EditText) v.findViewById(R.id.signin_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signinButton.getBackground().setAlpha(120);
        }
        else{
            signinPassword.setTypeface(Typeface.DEFAULT);
        }
        signinButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (flag==1){
            introSlider.setFirst(ISFIRSTLAUNCH,false);
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),IbnoxActivity.class));
        getActivity().finish();
        Toast.makeText(getActivity(),"You have successfully logged in",Toast.LENGTH_SHORT).show();
    }
}
