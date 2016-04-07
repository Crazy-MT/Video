package com.maotong.weibo.movie;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.widget.ColorTrackView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class MovieOneFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * 当fragment与activity发生关联时调用
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.init();
    }

    private String[] mTitles;
    private List<ColorTrackView> mTabs;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_one, container, false);
        initViews(view);
        initData();
        initEvents();
        return view;
    }

    private void initData() {
        mTitles = new String[3];

        mTitles[0] = getResources().getString(R.string.hot_showing);
        mTitles[1] = getResources().getString(R.string.trailer);
        mTitles[2] = getResources().getString(R.string.movie_list);


        mFragments = new TabFragment[mTitles.length];
        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(2);
    }

    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ColorTrackView left = mTabs.get(position);
                    ColorTrackView right = mTabs.get(position + 1);
                    Log.e("TAG", positionOffset+"");
                    left.setDirection(1);
                    right.setDirection(0);
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.id_movie_viewpager);
        mTabs = new ArrayList<>();
        mTabs.add((ColorTrackView) view.findViewById(R.id.id_movie_tab_01));
        mTabs.add((ColorTrackView) view.findViewById(R.id.id_movie_tab_02));
        mTabs.add((ColorTrackView) view.findViewById(R.id.id_movie_tab_03));
    }

    /**
     * 当activity的onCreate方法返回时调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTitles = null;
        mTabs = null;
        mViewPager = null;
        mAdapter = null;
        mFragments = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 与onAttach相对应，当Fragment与Activity关联被取消时调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
