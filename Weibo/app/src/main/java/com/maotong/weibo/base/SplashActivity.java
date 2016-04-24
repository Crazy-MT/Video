package com.maotong.weibo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.main.MainActivity;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.movie.pagelist.PageListModel;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.maotong.weibo.utils.Log;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.androidpn.client.ServiceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private View mContentView;
    private Context mContext;
    private Oauth2AccessToken mAccessToken;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        mContentView = findViewById(R.id.fullscreen_content);




        new Thread(new Runnable() {
            @Override
            public void run() {

                long beginTime = System.currentTimeMillis();

                List<MovieModel> mHotShowingList;
                List<MovieModel> mComingList;
                List<PageListModel> mPageList;
                List<List<MovieModel>> mPageListMovie = new ArrayList<>();

                //根据登录状态获得不同的movie数据
                mAccessToken = AccessTokenKeeper.readAccessToken(mContext);
                if (mAccessToken != null && mAccessToken.isSessionValid()) {
                    mHotShowingList = new JsonResolveUtils().getMovie(mAccessToken.getUid());
                    mComingList = new JsonResolveUtils().getComing(mAccessToken.getUid());
                } else {
                    mHotShowingList = new JsonResolveUtils().getMovie();
                    mComingList = new JsonResolveUtils().getComing();
                }

                mPageList = new JsonResolveUtils().getPageList();
                for (int i = 0; i < mPageList.size(); ++i) {
                    List<MovieModel> movieList;
                    movieList = new JsonResolveUtils().getPageListMovie(mPageList.get(i).getId());
                    mPageListMovie.add(movieList);
                }

                WeiBoApplication.getInstance().setHotShowingList(mHotShowingList);
                WeiBoApplication.getInstance().setComingList(mComingList);
                WeiBoApplication.getInstance().setPageListMovie(mPageListMovie);
                WeiBoApplication.getInstance().setPageList(mPageList);
                long endTime = System.currentTimeMillis();
                mHandler.sendEmptyMessageDelayed(0, waitTime(endTime - beginTime));
            }
        }).start();
    }

    private long waitTime(long time) {
        if (time > 1000) {
            return 0;
        } else {
            return 1000-time;
        }
    }
}
