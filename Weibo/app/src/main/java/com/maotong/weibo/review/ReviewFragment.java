package com.maotong.weibo.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.movie.hotshowing.HotShowingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class ReviewFragment extends android.support.v4.app.Fragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_review, container , false);
        mTabLayout = (TabLayout) view.findViewById(R.id.id_review_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.id_review_viewpager);
        setUpViewPager();
        mTabLayout.setupWithViewPager(mViewPager);


        return view;
    }

    private void setUpViewPager() {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new HotShowingFragment(), getResources().getString(R.string
                .review));
        mViewPager.setAdapter(adapter);
    }

    class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
