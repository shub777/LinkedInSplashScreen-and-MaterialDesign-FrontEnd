package com.example.shubhambhama.ibnox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IbnoxActivity extends AppCompatActivity {

    private static final int ANIM_TOOLBAR = 300;
    private Toolbar toolbar;
    private boolean doubleBackToExitBackPressedOnce = false;
    private Handler statusUpdateHandler;
    private Runnable statusUpdateRunnable;
    private Toast toast = null;
    private TextView mToolbarTitle;
    private boolean pendingToolBarAnimation;
    private HeadbarDataAdapter headbarDataAdapter;
    RecyclerView headbarRecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibnox);
        if (savedInstanceState == null) {
            pendingToolBarAnimation = true;
        }

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        customToolbar();

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        headbarDataAdapter=new HeadbarDataAdapter(this,getHeadbarData());
        headbarRecyclerview=(RecyclerView) findViewById(R.id.headbar_recyclerview);
        headbarRecyclerview.setHasFixedSize(true);
        headbarRecyclerview.setAdapter(headbarDataAdapter);
        headbarDataAdapter.notifyDataSetChanged();
        headbarRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        if (pendingToolBarAnimation) {
            toolbarAnimation();
            pendingToolBarAnimation = false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.notification:
                startActivity(new Intent(this, Notification.class));
                return true;
            case R.id.message_box:
                startActivity(new Intent(this, Message.class));
                return true;
            case R.id.search:
                Toast.makeText(getApplicationContext(), "Working in progress", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, search.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void customToolbar() {
        mToolbarTitle.setText("Ibnox");
        Typeface toolbarTitleTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lemonada-SemiBold.ttf");
        mToolbarTitle.setTypeface(toolbarTitleTypeface);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void toolbarAnimation() {
        int actionBarSize = getSupportActionBar().getHeight();
        toolbar.setTranslationY(-actionBarSize);
        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_TOOLBAR)
                .setStartDelay(300);
        mToolbarTitle.setTranslationY(-actionBarSize - 3);
        mToolbarTitle.animate()
                .translationY(0)
                .translationX(0)
                .setDuration(ANIM_TOOLBAR)
                .setStartDelay(400);
    }

    @Override
    public void onBackPressed() {
        //double onBackPressed app close
        if (doubleBackToExitBackPressedOnce) {
            toast.cancel();
            super.onBackPressed();
            return;
        }
        doubleBackToExitBackPressedOnce = true;
        toast = Toast.makeText(getApplicationContext(), "press twice to exit the app", Toast.LENGTH_SHORT);
        toast.show();

        statusUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                doubleBackToExitBackPressedOnce = false;
            }
        };
        statusUpdateHandler = new Handler();
        statusUpdateHandler.postDelayed(statusUpdateRunnable, 1000);
        //onBackPressed drawer close
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //removeCallBacks so that it cannot cause memory leak in app
        if (statusUpdateHandler != null) {
            toast.cancel();
            statusUpdateHandler.removeCallbacks(statusUpdateRunnable);
        }
    }

    private List<HeadbarData> getHeadbarData() {
        ArrayList<HeadbarData> data=new ArrayList<>();
        HeadbarData current=new HeadbarData();
        for (int i=0;i<10;i++){
            current.sethArticle("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).");
            current.sethArticleId(R.drawable.headbar_sample_image);
            current.sethIconid(R.drawable.header_face);
            current.sethReadtime("2 mins");
            current.sethTitle("Aenean feugiat enim et magna elementum blandit.");
            current.sethUpdatetime("2 days ago - ");
            current.sethWritername("Shubham Bhama");
            data.add(current);
        }
        current=null;
        System.gc();
        return data;
    }

}