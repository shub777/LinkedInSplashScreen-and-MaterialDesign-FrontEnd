package com.example.shubhambhama.ibnox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends Fragment implements View.OnClickListener {

    private static final String ISFIRSTLAUNCH = "isFirstLaunch";
    private static final String USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private SavedPreferenceFile introSlider;
    private View containerView;
    private RecyclerView recyclerView;
    private DrawerDataAdapter adapter;
    private String mTitleArray[];
    private int[] mIconArray = {R.drawable.ic_home_grey600_24dp, R.drawable.ic_playlist_check_grey600_24dp
            , R.drawable.ic_priority_high_grey600_24dp, R.drawable.ic_comment_question_outline_grey600_24dp, R.drawable.ic_settings_grey600_24dp
            , R.drawable.ic_help_circle_grey600_24dp};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introSlider = new SavedPreferenceFile(getActivity());
        if (!introSlider.checkIsFirst(ISFIRSTLAUNCH)) {
            introSlider.setFirst(ISFIRSTLAUNCH, false);
            introSlider.setFirst(USER_LEARNED_DRAWER, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawer_list);
        adapter = new DrawerDataAdapter(getActivity(), getData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                onClickListener(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "longClicked " + position, Toast.LENGTH_SHORT).show();
            }
        }));
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void onClickListener(View view, int position) {
        ImageView profilePicture = (ImageView) view.findViewById(R.id.header_image);
        TextView privacy = (TextView) view.findViewById(R.id.footer_privacy);
        TextView terms = (TextView) view.findViewById(R.id.footer_terms);
        TextView credits = (TextView) view.findViewById(R.id.footer_credits);
        switch (position) {
            case 0:
                profilePicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Profile.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                view.setOnClickListener(this);
                break;
            case 7:
                privacy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Privacy.class);
                        startActivity(intent);
                    }
                });
                terms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Terms.class);
                        startActivity(intent);
                    }
                });
                credits.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), Credits.class);
                                startActivity(intent);
                            }
                        });
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int pos = recyclerView.getChildAdapterPosition(view);
        switch (pos) {
            case 1:
                Toast.makeText(getActivity(), "Invoking home ", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(),Notification.class));
                break;
            case 2:
                Toast.makeText(getActivity(), "Invoking Subscribed ", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getActivity(), "Invoking Hightlighted ", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getActivity(), "Invoking Question and answer", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent intentsetting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intentsetting);
                break;
            case 6:
                Intent intenthelp = new Intent(getActivity(), Help.class);
                startActivity(intenthelp);
                break;
            default:
                break;
        }
    }

    private List<DrawerData> getData() {
        List<DrawerData> data = new ArrayList<>();
        DrawerData current1 = new DrawerData();
        current1.setHeader(true);
        data.add(current1);
        current1=null;
        System.gc();

        int count = 0;
        mTitleArray = getResources().getStringArray(R.array.drawer_item);
        for (String name : mTitleArray) {
            DrawerData current2=new DrawerData();
            current2.setTitle(name);
            current2.setIconId(mIconArray[count]);
            data.add(current2);
            current2=null;
            System.gc();
            count++;
        }

        DrawerData current3=new DrawerData();
        current3.setFooter(true);
        data.add(current3);
        current3=null;
        System.gc();
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (introSlider.checkIsFirst(USER_LEARNED_DRAWER)) {
                    introSlider.setFirst(USER_LEARNED_DRAWER, false);
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (introSlider.checkIsFirst(ISFIRSTLAUNCH) && introSlider.checkIsFirst(USER_LEARNED_DRAWER)) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        introSlider.setFirst(ISFIRSTLAUNCH, false);
        introSlider.setFirst(USER_LEARNED_DRAWER, false);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            Log.d("TAG", "Constructer invoked");
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.d("TAG", "onSingleTapUp" + e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                    Log.d("TAG", "onLongPress" + e);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("TAG", "onInterceptTouchEvent" + gestureDetector.onTouchEvent(e) + " " + e);

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("TAG", "onTouchEvent" + e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
