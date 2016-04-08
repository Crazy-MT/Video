package com.taguxdesign.maotong.myvideo.baoman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taguxdesign.maotong.myvideo.R;
import com.taguxdesign.maotong.myvideo.movie.DividerItemDecoration;
import com.taguxdesign.maotong.myvideo.VideoDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class BaoManFragment extends Fragment {

    private List<BaoManModel> mBaoManList;
    private Context mContext ;
    private RecyclerView mTvRv;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //setUpRecyclerView();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(List<BaoManModel> baoManList){
        mBaoManList = baoManList;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpRecyclerView();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tv , container , false);
        mTvRv = (RecyclerView) view.findViewById(R.id.id_recycler_view_tv);
        initData();
        return view;
    }

    private void setUpRecyclerView() {
        BaoManAdapter myAdapter = new BaoManAdapter(mBaoManList, getActivity());
        mTvRv.setAdapter(myAdapter);
        mTvRv.setLayoutManager(new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL , false));
        mTvRv.addItemDecoration(new  DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new BaoManAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity() , VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(VideoDetailActivity.HREF , mBaoManList.get(position).getHref());
                intent.putExtra( VideoDetailActivity.URL, bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }



    private void initData() {
        mBaoManList = new ArrayList<>();


        new Thread(new Runnable() {
            @Override
            public void run() {

//                //传统写法
                //mBaoManList = new BaoManHref().getAllHref();
//                mHandler.sendEmptyMessage(0);

                //EventBus用法
                EventBus.getDefault().post(new BaoManHref().getAllHref());
            }
        }).start();
    }
}
