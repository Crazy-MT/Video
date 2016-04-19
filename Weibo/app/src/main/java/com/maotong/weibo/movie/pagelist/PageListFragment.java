package com.maotong.weibo.movie.pagelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.utils.JsonResolveUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PageListFragment extends Fragment {
    private RecyclerView mRecycler;
    private List<PageListModel> mPageList;
    private List<List<MovieModel>> mPageListMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_list, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.id_page_list_recycler);
        if (mPageList == null || mPageListMovie == null) {
            initData();
        } else {
            setUpRecyclerView();
        }
        return view;

    }

    @Subscribe
    public void onEventMainThread(List<PageListModel> pageList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpRecyclerView();
            }
        });
    }

    private void setUpRecyclerView() {
        PageListAdapter adapter = new PageListAdapter(getContext(), mPageList, mPageListMovie);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //mRecycler.addItemDecoration(new DividerItemDecoration(getContext() , LinearLayoutManager.VERTICAL));
    }

    private void initData() {
        mPageList = new ArrayList<>();
        mPageListMovie = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPageList = new JsonResolveUtils().getPageList();
                for (int i = 0; i < mPageList.size(); ++i) {
                    List<MovieModel> movieList;
                    movieList = new JsonResolveUtils().getPageListMovie(mPageList.get(i).getId());
                    mPageListMovie.add(movieList);
                }
                EventBus.getDefault().post(mPageList);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
