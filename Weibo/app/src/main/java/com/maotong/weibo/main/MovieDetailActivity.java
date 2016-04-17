package com.maotong.weibo.main;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.movie.hotshowing.HotShowingModel;
import com.maotong.weibo.utils.JsonResolveUtils;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "电影";
    private TextView detail;
    private Context mContext;
    private HotShowingModel mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        mMovie = new HotShowingModel();
        mMovie = (HotShowingModel) bundle.get(MOVIE);
        final String movieName = mMovie.getName();
        int movieId = mMovie.getId();
        initData(mMovie);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movieName);
        loadBackdrop(mMovie.getLarge_poster_url());
        detail = (TextView) findViewById(R.id.id_detail);
        detail.setText(mMovie.getIntro());
    }

    private void initData(final HotShowingModel movie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMovie =  new JsonResolveUtils(mContext).getMovieDetail(movie.getId());
            }
        }).start();
    }

    private void loadBackdrop(String large_poster_url) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(large_poster_url).centerCrop().into(imageView);
    }
}
