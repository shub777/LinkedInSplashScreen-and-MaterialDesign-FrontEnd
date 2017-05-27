package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private RecyclerView recyclerView;
    private NotificationDataAdapter notificationDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        toolbar = (Toolbar) findViewById(R.id.notification_bar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        customToolbar();

        notificationDataAdapter = new NotificationDataAdapter(getData());
        recyclerView = (RecyclerView) findViewById(R.id.notification_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notificationDataAdapter);
        notificationDataAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void customToolbar() {
        mToolbarTitle.setText("Notification");
        Typeface toolbarTitleTypeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue_Medium.ttf");
        mToolbarTitle.setTypeface(toolbarTitleTypeface);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private List<NotificationData> getData() {
        List<NotificationData> data=new ArrayList<>();
        NotificationData current=new NotificationData();

        for (int i=0;i<30;i++){
            current.setImageView(R.drawable.notification_icon);
            current.setTextView("IbnoxUser1,IbnoxUser2 and 16 others peoples like your post that you had posted in Ibnox.");
            current.setTimeTextView("50 minutes ago");
            data.add(current);
        }
        return data;
    }
}
