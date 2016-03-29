package com.taguxdesign.maotong.myvideo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView videoRecyclerView;
    private List<VideoModel> videoMap;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sutUpRecyclerView();
        }
    };

    private void sutUpRecyclerView() {
        MyAdapter myAdapter = new MyAdapter(videoMap , this);
        videoRecyclerView.setAdapter(myAdapter);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        videoRecyclerView.addItemDecoration(new  DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this , NewVideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(NewVideoDetailActivity.HREF , videoMap.get(position).getHref());
                intent.putExtra( NewVideoDetailActivity.URL, bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initView() {
        videoRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
    }

    private void initData() {
        videoMap = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                videoMap = new AllHref().getAllHref();
                Log.e(TAG, "run: videoMap" + videoMap.size());
                mHandler.sendEmptyMessage(0);

            }
        }).start();
    }
}
