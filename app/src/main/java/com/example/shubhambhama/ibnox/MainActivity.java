package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ISFIRSTLAUNCH = "isFirstLaunch";
    public static int flag=0;
    Button login, signup;
    private ViewPager viewPager;
    private SavedPreferenceFile introslider;
    private ViewPagerAdapter viewPagerAdapter;
    private int layoutRes[];
    private LinearLayout dotsLayout;
    private TextView[] dots;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            changeBottomButtonColor(position);
            changeStatusBar();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private Vibrator vib;

    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        introslider = new SavedPreferenceFile(this);
        if (!introslider.checkIsFirst(ISFIRSTLAUNCH)) {
            introslider.setFirst(ISFIRSTLAUNCH, false);
            flag = 1;
            Intent intent = new Intent(this, IbnoxActivity.class);
            startActivity(intent);
            finish();
        }

        //if not then status becomes white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        //Initiate the variable
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        layoutRes = new int[]{R.layout.intro_screen1, R.layout.intro_screen2, R.layout.intro_screen3};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(40);
                flag = 0;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vib.vibrate(40);
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        //starting from zero position
        addBottomDots(0);
        changeBottomButtonColor(0);
        changeStatusBar();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void changeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void addBottomDots(int pos) {
        dots = new TextView[layoutRes.length];
        dotsLayout.removeAllViews();
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);

        //setting the html scripted dot to linearLayout
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(25);
            dots[i].setTextColor(colorInactive[pos]);
            dotsLayout.addView(dots[i]);
        }

        //Setting the active color
        if (dots.length > 0) {
            dots[pos].setTextColor(colorActive[pos]);
            if (pos == 0) {
                dots[pos].setTextColor(Color.parseColor("#424242"));
            }
        }
    }

    private void changeBottomButtonColor(int pos) {
        if (pos == 0) {
            login.setTextColor(Color.WHITE);
            signup.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            login.setTextColor(getResources().getColor(R.color.intro_slider_screen_textColor));
            signup.setTextColor(getResources().getColor(R.color.intro_slider_screen_textColor));
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (flag == 1) {
            introslider.setFirst(ISFIRSTLAUNCH, false);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        LayoutInflater inflater;
        Context context;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            inflater=LayoutInflater.from(context);
//            inflater=getLayoutInflater();
            View v = inflater.inflate(layoutRes[position], container, false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layoutRes.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }
}
