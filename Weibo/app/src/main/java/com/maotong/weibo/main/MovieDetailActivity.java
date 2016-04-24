package com.maotong.weibo.main;

        import android.content.Context;
        import android.content.Intent;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.design.widget.CoordinatorLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.maotong.weibo.R;
        import com.maotong.weibo.utils.JsonResolveUtils;
        import com.maotong.weibo.utils.Log;

        import org.apache.harmony.javax.security.auth.login.LoginException;
        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "电影";
    private Context mContext;
    private MovieModel mMovie;
    private RecyclerView mPeopleRecycle;
    private RelativeLayout mRefreshLayout;
    private TextView mNoDataText;
    private ProgressBar mRefreshPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;
        EventBus.getDefault().register(mContext);
        mRefreshLayout = (RelativeLayout) findViewById(R.id.id_refresh);
        mNoDataText = (TextView) findViewById(R.id.id_no_data);
        mRefreshPB = (ProgressBar) findViewById(R.id.id_refresh_progressbar);
        initData();

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        mMovie = new MovieModel();
        if (bundle != null && bundle.containsKey(MOVIE)) {
            mMovie = (MovieModel) bundle.get(MOVIE);
        }
        int isLike = 0;
        if (mMovie != null) {
            isLike = mMovie.getIsLike();
        }
        final int finalIsLike = isLike;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMovie = new JsonResolveUtils(mContext).getMovieDetail(mMovie.getId());
                mMovie.setIsLike(finalIsLike);
                EventBus.getDefault().post(new MovieDetailEvent(mMovie));
            }
        }).start();
    }

    private void initView(final MovieModel movie) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        TextView detail = (TextView) findViewById(R.id.id_detail);
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        TextView scoreText = (TextView) findViewById(R.id.id_movie_score);
        TextView scoreCountText = (TextView) findViewById(R.id.id_movie_score_count);
        TextView genreText = (TextView) findViewById(R.id.id_movie_genre);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (!TextUtils.isEmpty(movie.getName()) && collapsingToolbar != null) {
            collapsingToolbar.setTitle(movie.getName());
        }

        if (TextUtils.isEmpty(movie.getLarge_poster_url()) && imageView != null) {
            Glide.with(this).load(movie.getPoster_url()).centerCrop().into(imageView);
        } else if (imageView != null) {
            Glide.with(this).load(movie.getLarge_poster_url()).centerCrop().into(imageView);
        }

        detail.setText(movie.getIntro());
        scoreText.setText(movie.getScore() + "");
        scoreCountText.setText(movie.getScore_count() + "");
        genreText.setText(movie.getGenre());
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.id_movie_detail_video);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, VideoActivity.class);
                intent.putExtra(VideoActivity.INTENT_EXTRA, movie.getVideo_url());
                startActivity(intent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(final MovieDetailEvent movieDetailEvent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(movieDetailEvent.getMovie().getName())) {
                    mNoDataText.setVisibility(View.VISIBLE);
                    mRefreshPB.setVisibility(View.GONE);
                } else {
                    mRefreshLayout.setVisibility(View.GONE);
                    initView(movieDetailEvent.getMovie());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }
}
