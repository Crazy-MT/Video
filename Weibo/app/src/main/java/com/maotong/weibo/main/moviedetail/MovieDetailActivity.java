package com.maotong.weibo.main.moviedetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.api.Constants;
import com.maotong.weibo.base.WeiBoApplication;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.main.VideoActivity;
import com.maotong.weibo.personal.LoginStatusEvent;
import com.maotong.weibo.personal.UpLikeRecyclerEvent;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboShareException;
import com.sina.weibo.sdk.utils.Utility;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MovieDetailActivity extends AppCompatActivity implements IWeiboHandler.Response, CommentFragment.CommentListener {

    public static final String MOVIE = "电影";
    private Context mContext;
    private MovieModel mMovie;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TextView detail;
    ImageView imageView;
    TextView scoreText;
    TextView scoreCountText;
    TextView genreText;
    private RecyclerView mPeopleRecycle, mCommentRecycle;
    private RelativeLayout mRefreshLayout;
    private TextView mNoDataText;
    private ProgressBar mRefreshPB;
    private List<Actor> mActorList;
    private List<Comment> mCommentList;
    private LinearLayout mLikeLayout, mCommentLayout, mShareLayout;
    private ImageView mLikeImg;
    private TextView mLikeText;
    private int isLike = 0;
    private Oauth2AccessToken mAccessToken;

    /**
     * 微博微博分享接口实例
     */
    IWeiboShareAPI mWeiboShareAPI = null;
    private static String HANDLER_LIKE = "like";
    private static String HANDLER_LIKE_YES = "yes";
    private static String HANDLER_LIKE_NO = "no";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            if (HANDLER_LIKE_YES.equals(bundle.get(HANDLER_LIKE))) {
                isLike = 1;
            } else {
                isLike = 0;
            }
            setBottomLayout();
            EventBus.getDefault().post(new UpLikeRecyclerEvent(true));
            EventBus.getDefault().post(new LoginStatusEvent(true, "", LoginStatusEvent
                    .LOGIN_SUCCESS));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;
        EventBus.getDefault().register(mContext);
        mRefreshLayout = (RelativeLayout) findViewById(R.id.id_refresh);
        mNoDataText = (TextView) findViewById(R.id.id_no_data);
        mRefreshPB = (ProgressBar) findViewById(R.id.id_refresh_progressbar);
        mCommentRecycle = (RecyclerView) findViewById(R.id.id_comment_recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        detail = (TextView) findViewById(R.id.id_detail);
        imageView = (ImageView) findViewById(R.id.backdrop);
        scoreText = (TextView) findViewById(R.id.id_movie_score);
        scoreCountText = (TextView) findViewById(R.id.id_movie_score_count);
        genreText = (TextView) findViewById(R.id.id_movie_genre);
        mPeopleRecycle = (RecyclerView) findViewById(R.id.id_movie_people);

        mLikeLayout = (LinearLayout) findViewById(R.id.id_movie_detail_like);
        mCommentLayout = (LinearLayout) findViewById(R.id.id_movie_detail_comment);
        mShareLayout = (LinearLayout) findViewById(R.id.id_movie_detail_share);
        mLikeImg = (ImageView) findViewById(R.id.id_movie_detail_like_img);
        mLikeText = (TextView) findViewById(R.id.id_movie_detail_like_text);
        initWeiBoShare(savedInstanceState);
        initData();

        mLikeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLikeMovie(mMovie);
            }
        });

        mShareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShareMovie(mMovie);
            }


        });

        mCommentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCommentMovie(mMovie);
            }

        });

    }

    private void initWeiBoShare(Bundle savedInstanceState) {

        // 创建微博分享接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, Constants.APP_KEY);
        // 如果未安装微博客户端，设置下载微博对应的回调
        if (!mWeiboShareAPI.isWeiboAppInstalled()) {
            mWeiboShareAPI.registerWeiboDownloadListener(new IWeiboDownloadListener() {
                @Override
                public void onCancel() {
                    Toast.makeText(mContext,
                            R.string.weibosdk_demo_cancel_download_weibo,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 当 Activity 被重新初始化时（该 Activity 处于后台时，可能会由于内存不足被杀掉了），
        // 需要调用 {@link IWeiboShareAPI#handleWeiboResponse} 来接收微博客户端返回的数据。
        // 执行成功，返回 true，并调用 {@link IWeiboHandler.Response#onResponse}；
        // 失败返回 false，不调用上述回调
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }
    }

    /**
     * @see {@link Activity#onNewIntent}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // 从当前应用唤起微博并进行分享后，返回到当前应用时，需要在此处调用该函数
        // 来接收微博客户端返回的数据；执行成功，返回 true，并调用
        // {@link IWeiboHandler.Response#onResponse}；失败返回 false，不调用上述回调
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }

    /**
     * 接收微客户端博请求的数据。
     * 当微博客户端唤起当前应用并进行分享时，该方法被调用。
     *
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */
    @Override
    public void onResponse(BaseResponse baseResp) {
        switch (baseResp.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                Toast.makeText(this, R.string.weibosdk_demo_toast_share_success, Toast.LENGTH_LONG).show();
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                Toast.makeText(this, R.string.weibosdk_demo_toast_share_canceled, Toast.LENGTH_LONG).show();
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                Toast.makeText(this,
                        getString(R.string.weibosdk_demo_toast_share_failed) + "Error Message: " + baseResp.errMsg,
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void initData() {

        mAccessToken = AccessTokenKeeper.readAccessToken(mContext);

        Bundle bundle = getIntent().getExtras();
        mMovie = new MovieModel();
        mActorList = new ArrayList<>();
        if (bundle != null && bundle.containsKey(MOVIE)) {
            mMovie = (MovieModel) bundle.get(MOVIE);
        }

        if (mMovie != null) {
            isLike = mMovie.getIsLike();
        }
        final int finalIsLike = isLike;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMovie = new JsonResolveUtils(mContext).getMovieDetail(mMovie.getId());
                mActorList = new JsonResolveUtils(mContext).getActor(mMovie.getId());
                mCommentList = new JsonResolveUtils(mContext).getComment(mMovie.getId());
                mMovie.setIsLike(finalIsLike);
                EventBus.getDefault().post(new MovieDetailEvent(mMovie, mActorList, mCommentList));
            }
        }).start();
    }

    private void initView(final MovieModel movie, List<Actor> actorList, List<Comment> commentList) {

        if (movie != null) {
            setTopLayout(movie);
        }

        if (actorList != null) {
            setRecyclerView(actorList);
        }

        if(commentList != null){
            setRecyclerViewComment(commentList);
        }

        setBottomLayout();


    }

    private void setRecyclerViewComment(List<Comment> commentList) {
        CommentAdapter commentAdapter = new CommentAdapter(mContext, commentList);
        mCommentRecycle.setAdapter(commentAdapter);
        mCommentRecycle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL
                , false));
    }

    private void setCommentMovie(MovieModel movie) {
        CommentFragment commentFragment = new CommentFragment();
        commentFragment.show(getSupportFragmentManager(), "CommentFragment");
    }

    private void setShareMovie(MovieModel movie) {
        try {
            // 检查微博客户端环境是否正常，如果未安装微博，弹出对话框询问用户下载微博客户端
            if (mWeiboShareAPI.checkEnvironment(true)) {

                // 注册第三方应用 到微博客户端中，注册成功后该应用将显示在微博的应用列表中。
                // 但该附件栏集成分享权限需要合作申请，详情请查看 Demo 提示
                mWeiboShareAPI.registerApp();
                if (mWeiboShareAPI.isWeiboAppSupportAPI()) {
                    //sendMultiMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo,
                    //       hasVoice);
                    // 1. 初始化微博的分享消息
                    WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
                    weiboMessage.mediaObject = getVideoObj(movie);
                    TextObject textObject = new TextObject();
                    textObject.text = movie.getIntro();
                    weiboMessage.textObject = textObject;
                    // 2. 初始化从第三方到微博的消息请求
                    SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
                    // 用transaction唯一标识一个请求
                    request.transaction = String.valueOf(System.currentTimeMillis());
                    request.multiMessage = weiboMessage;

                    // 3. 发送请求消息到微博，唤起微博分享界面
                    mWeiboShareAPI.sendRequest(request);

                } else {
                    Toast.makeText(this, R.string.weibosdk_demo_not_support_api_hint, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (WeiboShareException e) {
            e.printStackTrace();
            Toast.makeText(MovieDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 创建多媒体（视频）消息对象。
     *
     * @return 多媒体（视频）消息对象。
     */
    private VideoObject getVideoObj(MovieModel movie) {
        // 创建媒体消息
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = movie.getName();
        videoObject.description = movie.getGenre();

        // 设置 Bitmap 类型的图片到视频对象里
        videoObject.setThumbImage(drawable2Bitmap(getResources().getDrawable(R.mipmap
                .ic_launcher)));
        videoObject.actionUrl = movie.getVideo_url();
        videoObject.dataUrl = "www.weibo.com";
        videoObject.dataHdUrl = "www.weibo.com";
        videoObject.duration = 10;
        videoObject.defaultText = "Vedio 默认文案";
        return videoObject;
    }

    Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    private void setLikeMovie(final MovieModel movie) {
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (isLike == 0) {
                        boolean isSuccess = new JsonResolveUtils(mContext).setLikeMovie
                                (mAccessToken.getUid(), movie.getId(), true);
                        if (isSuccess) {
                            //更新UI
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putString(HANDLER_LIKE, HANDLER_LIKE_YES);
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                        }
                    } else {
                        boolean isSuccess = new JsonResolveUtils(mContext).setLikeMovie
                                (mAccessToken.getUid(), movie.getId(), false);
                        if (isSuccess) {
                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putString(HANDLER_LIKE, HANDLER_LIKE_NO);
                            message.setData(bundle);
                            mHandler.sendMessage(message);
                        }
                    }
                }
            }).start();
        } else {
            Toast.makeText(MovieDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTopLayout(final MovieModel movie) {
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
        scoreCountText.setText(movie.getScore_count() + "人点评");
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

    private void setBottomLayout() {

        if (isLike == 1) {
            mLikeImg.setImageResource(R.drawable.movie_like);
        } else {
            mLikeImg.setImageResource(R.drawable.movie_unlike);
        }

    }

    private void setRecyclerView(List<Actor> actorList) {
        ActorAdapter actorAdapter = new ActorAdapter(mContext, actorList);
        mPeopleRecycle.setAdapter(actorAdapter);
        mPeopleRecycle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .HORIZONTAL
                , false));
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
                    initView(movieDetailEvent.getMovie(), movieDetailEvent.getActorList(), movieDetailEvent.getmCommentList());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(mContext);
    }

    @Override
    public void onCommentComplete(String comment) {
        //Toast.makeText(MovieDetailActivity.this, comment, Toast.LENGTH_SHORT).show();
        if (mAccessToken != null && mAccessToken.isSessionValid() && mMovie != null) {
            //发送评论信息
            OkHttpUtils.post().url(WeiBoApplication.getInstance().getBASE_URL() + "/CommentServlet")
                    .addParams("userid", mAccessToken.getUid())
                    .addParams("comment", comment)
                    .addParams("movieid", mMovie.getId() + "").build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    Toast.makeText(MovieDetailActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MovieDetailActivity.this, response, Toast.LENGTH_SHORT).show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mCommentList = new JsonResolveUtils(mContext).getComment(mMovie.getId());
                            EventBus.getDefault().post(new MovieDetailEvent(mMovie, mActorList, mCommentList));
                        }
                    }).start();
                }
            });
        } else {
            Toast.makeText(MovieDetailActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }
    }
}
