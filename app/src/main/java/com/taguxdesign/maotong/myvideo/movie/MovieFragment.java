package com.taguxdesign.maotong.myvideo.movie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taguxdesign.maotong.myvideo.VideoDetailActivity;
import com.taguxdesign.maotong.myvideo.R;
import com.taguxdesign.maotong.myvideo.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private static final String TAG = "MovieFragment";
    private List<VideoModel> videoMap;
    private Context mContext;
    private RecyclerView movieRecyclerView;
    View view;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sutUpRecyclerView();
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e(TAG, "onAttach: " );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e(TAG, "onCreateView: ");
        view = inflater.inflate(R.layout.fragment_movie, container, false);
        movieRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        if (videoMap == null){
            initData();
        } else {
            sutUpRecyclerView();
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " );
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: ");
    }

    private void sutUpRecyclerView() {
        MyAdapter myAdapter = new MyAdapter(videoMap, getActivity());
        movieRecyclerView.setAdapter(myAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        movieRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(VideoDetailActivity.HREF, videoMap.get(position).getHref());
                intent.putExtra(VideoDetailActivity.URL, bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    private void initData() {
        videoMap = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                videoMap = new MovieHref().getAllHref();
                mHandler.sendEmptyMessage(0);

            }
        }).start();
    }
}
