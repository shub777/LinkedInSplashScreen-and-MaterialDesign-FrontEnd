package com.example.shubhambhama.ibnox;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhambhama.ibnox.customView.ExpandableTextView;

public class Profile extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TabLayout mtablayout;
    private TextView mDescription;
    private boolean isTextViewClicked=false;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mtablayout = (TabLayout) findViewById(R.id.profile_tablayout);

        customToolbar();
        setupTabLayout();

        text="It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.";

        ExpandableTextView expandableTextView=(ExpandableTextView) findViewById(R.id.profile_description);
        expandableTextView.setText(text);
//
//        mDescription.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupTabLayout() {
        mtablayout.addTab(mtablayout.newTab().setIcon(R.drawable.ic_priority_high_grey600_24dp));
        mtablayout.addTab(mtablayout.newTab().setIcon(R.drawable.ic_comment_question_outline_grey600_24dp));
        mtablayout.addTab(mtablayout.newTab().setIcon(R.drawable.ic_home_grey600_24dp));
        mtablayout.addTab(mtablayout.newTab().setIcon(R.drawable.ic_help_circle_grey600_24dp));
    }

    private void customToolbar() {
        mToolbarTitle.setText("Ibnox");
        Typeface toolbarTitleTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lemonada-SemiBold.ttf");
        mToolbarTitle.setTypeface(toolbarTitleTypeface);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.profile_description:
//                if (isTextViewClicked){
//                    mDescription.setMaxLines(2);
//                    isTextViewClicked=false;
//                }
//                else {
//                    mDescription.setMaxLines(Integer.MAX_VALUE);
//                    isTextViewClicked=true;
//                }
//                break;
//            default:
//                break;
//        }

    }
}
