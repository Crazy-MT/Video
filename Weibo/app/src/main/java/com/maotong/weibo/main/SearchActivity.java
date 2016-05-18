package com.maotong.weibo.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.main.moviedetail.MovieDetailActivity;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.maotong.weibo.widget.FlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SearchActivity extends AppCompatActivity {
    private Context mContext ;
    private FlowLayout mFlow ;
    private MovieModel movie;
    private RelativeLayout mMovieLayout;
    private ImageView movieBg;
    private TextView mScore;
    private TextView mName;
    private TextView mComment;


    private String ret;

    private DataBean data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = this;
        mFlow = (FlowLayout) findViewById(R.id.id_search_flow);
        mMovieLayout = (RelativeLayout) findViewById(R.id.id_search_movie);
        movieBg = (ImageView) findViewById(R.id.id_search_movie_bg);
        mScore = (TextView) findViewById(R.id.id_search_movie_score);
        mName = (TextView) findViewById(R.id.id_search_movie_name);
        mComment = (TextView) findViewById(R.id.id_search_movie_comment_text);

        EventBus.getDefault().register(mContext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_search_toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle("搜索");
    }

    @Subscribe
    public void onEventMainThread(final SearchMovieEvent searchMovieEvent){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpMovie(searchMovieEvent.getMovie());
            }
        });
    }

    private void setUpMovie(final MovieModel movie){
        mMovieLayout.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(movie.getLarge_poster_url()).into(movieBg);
        mScore.setText(movie.getScore()+"分");
        mName.setText(movie.getName());
        mComment.setText(movie.getScore_count()+"人评论");

        mMovieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //movie缺少isLike字段

                //跳转到电影页面
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MovieDetailActivity.MOVIE, movie);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                //输入内容发送后台   后台查询后返回内容
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        movie = new JsonResolveUtils(mContext).getSearchMovie(query);
                        EventBus.getDefault().post(new SearchMovieEvent(movie));
                    }
                }).start();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menuItem , new MenuItemCompat.OnActionExpandListener(){
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mFlow.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mFlow.setVisibility(View.VISIBLE);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * genre : 爱情 / 科幻
         * page_list_id : 0
         * score : 8.300000190734863
         * score_count : 1510216
         * intro : 周星驰电影《美人鱼》，主演邓超、罗志祥、张雨绮、林允，正式定档2016年2月8日，大年初一，人鱼无敌。
         * id : 176971
         * poster_url : http://weiyinyue.music.sina.com.cn/movie_cover/176971_big.jpg
         * video_url : http://v.iask.com/v_play_ipad.php?vid=139734603
         * is_coming : 0
         * name : 美人鱼
         * release_date :
         * is_Like : 0
         * is_showing : 0
         * large_poster_url : http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/176971_big.jpg
         */

        private MovieBean movie;
        private String actors;
        private String director;

        public MovieBean getMovie() {
            return movie;
        }

        public void setMovie(MovieBean movie) {
            this.movie = movie;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public static class MovieBean {
            private String genre;
            private int page_list_id;
            private double score;
            private int score_count;
            private String intro;
            private int id;
            private String poster_url;
            private String video_url;
            private int is_coming;
            private String name;
            private String release_date;
            private int is_Like;
            private int is_showing;
            private String large_poster_url;

            public String getGenre() {
                return genre;
            }

            public void setGenre(String genre) {
                this.genre = genre;
            }

            public int getPage_list_id() {
                return page_list_id;
            }

            public void setPage_list_id(int page_list_id) {
                this.page_list_id = page_list_id;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public int getScore_count() {
                return score_count;
            }

            public void setScore_count(int score_count) {
                this.score_count = score_count;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPoster_url() {
                return poster_url;
            }

            public void setPoster_url(String poster_url) {
                this.poster_url = poster_url;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public int getIs_coming() {
                return is_coming;
            }

            public void setIs_coming(int is_coming) {
                this.is_coming = is_coming;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public int getIs_Like() {
                return is_Like;
            }

            public void setIs_Like(int is_Like) {
                this.is_Like = is_Like;
            }

            public int getIs_showing() {
                return is_showing;
            }

            public void setIs_showing(int is_showing) {
                this.is_showing = is_showing;
            }

            public String getLarge_poster_url() {
                return large_poster_url;
            }

            public void setLarge_poster_url(String large_poster_url) {
                this.large_poster_url = large_poster_url;
            }
        }
    }
}
