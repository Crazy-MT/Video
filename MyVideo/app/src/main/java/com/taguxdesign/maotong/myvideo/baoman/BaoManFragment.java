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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taguxdesign.maotong.myvideo.R;
import com.taguxdesign.maotong.myvideo.movie.DividerItemDecoration;
import com.taguxdesign.maotong.myvideo.VideoDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class BaoManFragment extends Fragment {

    private List<BaoManModel> mBaoManList;
    private Context mContext ;
    private RecyclerView mTvRv;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sutUpRecyclerView();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tv , container , false);
        mTvRv = (RecyclerView) view.findViewById(R.id.id_recycler_view_tv);
        initData();
        return view;
    }

    private void sutUpRecyclerView() {
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
                mBaoManList = new BaoManHref().getAllHref();
                mHandler.sendEmptyMessage(0);

            }
        }).start();
    }
}
