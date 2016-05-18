package com.maotong.weibo.review;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;
import com.maotong.weibo.utils.JsonResolveUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AllReviewFragment extends Fragment {
    private RecyclerView mRecycler;
    private List<ReviewModel> mReviewModelList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_review, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.id_review_recycler);

        if (mReviewModelList == null) {
            initData();
        } else {
            setUpRecyclerView();
        }

        return view;
    }

    private void setUpRecyclerView() {
        ReviewAdapter adapter = new ReviewAdapter(getContext(), mReviewModelList);
        mRecycler.setAdapter(adapter);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Subscribe
    public void onEventMainThread(List<ReviewModel> mReviewModelList){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpRecyclerView();
            }
        });
    }

    public void initData() {
        if (mReviewModelList == null) {
            mReviewModelList = new ArrayList<>();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mReviewModelList = new JsonResolveUtils().getComment();
                EventBus.getDefault().post(mReviewModelList);
            }
        }).start();
    }

    public void RecyclerNotify(){
        mRecycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
