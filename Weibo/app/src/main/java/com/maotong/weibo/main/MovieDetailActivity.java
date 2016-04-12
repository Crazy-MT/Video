package com.maotong.weibo.main;

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

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "电影";
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();
        HotShowingModel movie = new HotShowingModel();
        movie = (HotShowingModel) bundle.get(MOVIE);
        final String movieName = movie.getName();
        int movieId = movie.getId();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movieName);
        Log.e("tag" , movie.toString());
        loadBackdrop(movie.getLarge_poster_url());
        detail = (TextView) findViewById(R.id.id_detail);
        detail.setText(movie.getIntro());
    }

    private void loadBackdrop(String large_poster_url) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(large_poster_url).centerCrop().into(imageView);
    }
}
