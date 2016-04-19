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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.maotong.weibo.utils.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "电影";
    private TextView detail;
    private Context mContext;
    private MovieModel mMovie;
    private CoordinatorLayout mMainContentLayout;
    private RecyclerView mPeopleRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;
        EventBus.getDefault().register(mContext);
        mMainContentLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        mMainContentLayout.setVisibility(View.GONE);

        initData();
//id=99946, name='功夫熊猫3', genre='喜剧 / 动作 / 动画 / 家庭 / 冒险', intro='在新一集故事里，与阿宝失散已久的生父突然现身，重逢的父子二人一起来到了一片不为人知的熊猫乐土。在这里，阿宝遇到了很多可爱有趣的熊猫同类。当拥有神秘力量的大反派“绿眼牛”企图横扫神州大地，残害所有功夫高手时，阿宝必须迎难而上，把那些热衷享乐、笨手笨脚的熊猫村民训练成一班所向披靡的功夫熊猫！', poster_url='http://weiyinyue.music.sina.com.cn/movie_cover/99946_big.jpg', large_poster_url='http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/99946_big.jpg', score=8.2, score_count=148518, isLike=0, release_date='', video_url='http://v.iask.com/v_play_ipad.php?vid=139454485', director='余仁英', actors='杰克·布莱克 / 安吉丽娜·朱莉 / 达斯汀·霍夫曼 / 成龙 / 塞斯·罗根 / 刘玉玲 / 大卫·克罗斯/'}

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        mMovie = new MovieModel();
        mMovie = (MovieModel) bundle.get(MOVIE);
        final int isLike = mMovie.getIsLike();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMovie =  new JsonResolveUtils(mContext).getMovieDetail(mMovie.getId());
                Log.e(mMovie.toString());
                mMovie.setIsLike(isLike);
                EventBus.getDefault().post(new MovieDetailEvent(mMovie));
            }
        }).start();
    }

    private void initView(final MovieModel movie) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        detail = (TextView) findViewById(R.id.id_detail);
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        TextView scoreText = (TextView) findViewById(R.id.id_movie_score);
        TextView scoreCountText = (TextView) findViewById(R.id.id_movie_score_count);
        TextView genreText = (TextView) findViewById(R.id.id_movie_genre);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(movie.getName());

        if ("".equals(movie.getLarge_poster_url())){
            Glide.with(this).load(movie.getPoster_url()).centerCrop().into(imageView);
        } else {
            Glide.with(this).load(movie.getLarge_poster_url()).centerCrop().into(imageView);
        }


        detail.setText(movie.getIntro());
        scoreText.setText(movie.getScore()+"");
        scoreCountText.setText(movie.getScore_count()+"");
        genreText.setText(movie.getGenre());
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.id_movie_detail_video);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext , VideoActivity.class);
                intent.putExtra("video_url" , movie.getVideo_url());
                startActivity(intent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(final MovieDetailEvent movieDetailEvent){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (movieDetailEvent.getMovie() == null){
                } else {
                    mMainContentLayout.setVisibility(View.VISIBLE);
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
