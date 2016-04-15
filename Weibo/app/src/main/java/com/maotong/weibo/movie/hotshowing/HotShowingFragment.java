package com.maotong.weibo.movie.hotshowing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.main.MovieDetailActivity;
import com.maotong.weibo.personal.LoginStatusEvent;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class HotShowingFragment extends Fragment {
    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipe;
    private List<HotShowingModel> mHotShowingList;
    private Oauth2AccessToken mAccessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_showing, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.id_hot_showing_recycler);
        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.id_hot_showing_swipe);
        if (mHotShowingList == null) {
            initData();
        } else {
            setUpRecyclerView();
        }

        mSwipe.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mSwipe.isRefreshing()) {
                    initData();
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 登录或者注销之后，会发送LoinStatusEvent事件
     * @param loginStatusEvent
     */
    @Subscribe
    public void onEventMainThread(LoginStatusEvent loginStatusEvent){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }

    @Subscribe
    public void onEventMainThread(List<HotShowingModel> hotShowingModelList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpRecyclerView();
            }
        });
    }

    private void setUpRecyclerView() {
        mSwipe.setRefreshing(false);
        HotShowingAdapter adapter = new HotShowingAdapter(getContext(), mHotShowingList);
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setOnItemClickListener(new HotShowingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到电影页面
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MovieDetailActivity.MOVIE, mHotShowingList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void initData() {
        if (mHotShowingList == null) {
            mHotShowingList = new ArrayList<>();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                //根据登录状态获得不同的movie数据
                mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
                if (mAccessToken != null && mAccessToken.isSessionValid()) {
                    mHotShowingList = new JsonResolveUtils().getMovie(mAccessToken.getUid());
                } else {
                    mHotShowingList = new JsonResolveUtils().getMovie();
                }

                EventBus.getDefault().post(mHotShowingList);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
