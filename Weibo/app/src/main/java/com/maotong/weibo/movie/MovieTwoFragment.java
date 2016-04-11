package com.maotong.weibo.movie;

import android.app.Activity;
import android.content.Context;
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
import com.maotong.weibo.movie.coming.ComingFragment;
import com.maotong.weibo.movie.hotshowing.HotShowingFragment;
import com.maotong.weibo.movie.pagelist.PageListFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class MovieTwoFragment extends Fragment implements View.OnClickListener {

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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_two, container, false);
        initViews(view);
        initData();
        initEvents();
        return view;
    }

    private void initData() {
    }

    private void initEvents() {
    }

    private void initViews(View view) {
        ViewPager mViewPager;
        mViewPager = (ViewPager) view.findViewById(R.id.id_movie_viewpager_two);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.id_movie_tabs_two);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getFragmentManager());
        adapter.addFragment(new HotShowingFragment(), getResources().getString(R.string.hot_showing));
        adapter.addFragment(new ComingFragment(), getResources().getString(R.string.trailer));
        adapter.addFragment(new PageListFragment(), getResources().getString(R.string.movie_list));
        viewPager.setAdapter(adapter);
    }
    static class Adapter extends FragmentPagerAdapter {
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
