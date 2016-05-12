package com.maotong.weibo.personal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.main.MainActivity;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.utils.FastBlur;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.sina.weibo.sdk.openapi.legacy.UsersAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 * to-do-list -->  重构代码
 */
public class PersonalFragment extends android.support.v4.app.Fragment {


    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    /**
     * 登出操作对应的listener
     */
    private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
    private IUserApi mIUserApi = new IUserApi();

    private Context mContext;
    private TextView mUserName;
    private ImageView mIconBlur, mIcon;
    private UserModel userModel;
    private boolean isLogin;
    private Button mLogout;
    private RecyclerView mLikeRecycler;
    private RecyclerView mCommentRecycler;
    private List<MovieModel> mMovieList;
    private List<MovieModel> mCommentMovieList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mContext = getContext();
    }

    @Subscribe
    public void onEventMainThread(final LoginStatusEvent loginStatusEvent) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUserView(loginStatusEvent.ismIsLogin());
                if (LoginStatusEvent.LOGOUT_SUCCESS.equals(loginStatusEvent.getDoLogout())) {
                    Toast.makeText(mContext, "注销成功", Toast.LENGTH_LONG).toString();
                } else if (LoginStatusEvent.LOGOUT_ERROR.equals(loginStatusEvent.getDoLogout())) {
                    Toast.makeText(mContext, "后台注销失败", Toast.LENGTH_LONG).toString();
                }
            }
        });
    }

    @Subscribe
    public void onEventMainThread(final PersonalMovieEvent movieEvent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                PersonalLikeAdapter adapter = new PersonalLikeAdapter(mContext, movieEvent.getMovieList());
                mLikeRecycler.setAdapter(adapter);
                mLikeRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                adapter = new PersonalLikeAdapter(mContext, movieEvent.getMovieCommentList());
                mCommentRecycler.setAdapter(adapter);
                mCommentRecycler.setLayoutManager(new LinearLayoutManager(mContext ,
                        LinearLayoutManager.HORIZONTAL,false));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        initData();
        initSetUpView();
        initEvent(view);
        return view;
    }

    //初始化控件
    private void initView(View view) {
        mUserName = (TextView) view.findViewById(R.id.id_personal_user_name);
        mIconBlur = (ImageView) view.findViewById(R.id.id_personal_user_icon_blur);
        mIcon = (ImageView) view.findViewById(R.id.id_personal_user_icon);
        mLogout = (Button) view.findViewById(R.id.id_personal_logout);
        mLikeRecycler = (RecyclerView) view.findViewById(R.id.id_personal_like_list);
        mCommentRecycler = (RecyclerView) view.findViewById(R.id.id_personal_comment_list);
    }

    //初始化数据
    private void initData() {
        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
        // 第一次启动本应用，AccessToken 不可用
        mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            //已经登录
            isLogin = true;
        } else {
            //未登录
            isLogin = false;
        }
    }

    //更新view
    private void initSetUpView() {
        EventBus.getDefault().post(new LoginStatusEvent(isLogin));
    }

    private void initEvent(View view) {
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LogoutAPI(mAccessToken).logout(mLogoutListener);
            }
        });

        //头像
        view.findViewById(R.id.id_personal_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    authLogin();
                } else {
                    Toast.makeText(mContext, "已登录", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUserView(boolean isLogin) {
        if (isLogin) {
            //访问接口结果会回调到mIUserApi
            new UsersAPI(mAccessToken).show(Long.valueOf(mAccessToken.getUid()), mIUserApi);
            mLogout.setVisibility(View.VISIBLE);
            mLikeRecycler.setVisibility(View.VISIBLE);
        } else {
            //Toast.makeText(mContext, R.string.weibosdk_demo_access_token_is_empty, Toast.LENGTH_LONG).show();
            mLogout.setVisibility(View.GONE);
            //清空界面信息
            mIcon.setImageResource(R.mipmap.weibomovie_my_icon_head_default);
            mIconBlur.setImageResource(R.mipmap.weibomovie_my_icon_head_default);
            mUserName.setText("请登录");
            mLikeRecycler.setVisibility(View.GONE);
        }
        setMovieRecycler(isLogin);
    }


    private void setMovieRecycler(final boolean isLogin) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isLogin) {
                    mMovieList = new JsonResolveUtils(mContext).getMovieLike(mAccessToken.getUid());
                    mCommentMovieList = new JsonResolveUtils(mContext).getCommentMovieByUserId
                            (mAccessToken.getUid());
                    EventBus.getDefault().post(new PersonalMovieEvent(mMovieList , mCommentMovieList));
                } else {
                    //mLikeRecycler.setVisibility(View.GONE);
                }
            }
        }).start();
    }

    /**
     * 登出按钮的监听器，接收登出处理结果。（API 请求结果的监听器）
     */
    private class LogOutRequestListener implements RequestListener {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String value = obj.getString("result");
                    if ("true".equalsIgnoreCase(value)) {
                        AccessTokenKeeper.clear(mContext);
                        Toast.makeText(mContext, mContext.getString(R.string.weibosdk_demo_logout_success), Toast.LENGTH_LONG).show();
                        isLogin = false;
                        //注销信息发送后台
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isLogout = new JsonResolveUtils(mContext).logout(userModel);
                                if (isLogout) {
                                    EventBus.getDefault().post(new LoginStatusEvent(isLogin, LoginStatusEvent.LOGOUT_SUCCESS));
                                } else {
                                    EventBus.getDefault().post(new LoginStatusEvent(isLogin, LoginStatusEvent.LOGOUT_ERROR));
                                }
                            }
                        }).start();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onComplete4binary(ByteArrayOutputStream responseOS) {
            // Do nothing
        }

        @Override
        public void onIOException(IOException e) {
            Toast.makeText(mContext, mContext.getString(R.string.weibosdk_demo_logout_failed), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(WeiboException e) {
            Toast.makeText(mContext, mContext.getString(R.string.weibosdk_demo_logout_failed), Toast.LENGTH_LONG).show();
        }
    }


    private void setUpCommentRecycler() {

    }

    @Subscribe
    public void onEventMainThread(UpLikeRecyclerEvent upLikeRecyclerEvent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpLikeRecycler();
            }
        });
    }


    private void setUpLikeRecycler() {
        setMovieRecycler(true);
    }

    private void authLogin() {
        //登录结果会从MainActivity回调到onActivityResult
        ((MainActivity) getActivity()).Auth();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            isLogin = true;
            EventBus.getDefault().post(new LoginStatusEvent(isLogin, "", LoginStatusEvent.LOGIN_SUCCESS));
        }
    }

    private class IUserApi implements RequestListener {

        @Override
        public void onComplete(String response) {
            setUser(response);
            setUpIcon();
        }

        private void setUser(String response) {
            userModel = new UserModel();
            JSONObject json;
            try {
                json = new JSONObject(response);
                userModel.setWeibo_id(Long.valueOf(mAccessToken.getUid()));
                userModel.setUserName(json.getString("screen_name"));
                userModel.setUserIcon(json.getString("avatar_hd"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isLogin = new JsonResolveUtils(mContext).setUser(userModel);
                    if (!isLogin) {
                        //Toast.makeText(mContext, "后台记录失败", Toast.LENGTH_LONG).show();
                        Log.e("tag", "run: 后台记录失败");
                    }
                }
            }).start();

        }

        @Override
        public void onComplete4binary(ByteArrayOutputStream responseOS) {
            mUserName.setText(responseOS.toString());
        }

        @Override
        public void onIOException(IOException e) {
            mUserName.setText(e.toString());
        }

        @Override
        public void onError(WeiboException e) {
            mUserName.setText(e.toString());
        }
    }

    private void setUpIcon() {
        if (userModel != null) {
            mUserName.setMovementMethod(new ScrollingMovementMethod());
            mUserName.setText(userModel.getUserName());
            Glide.with(mContext).load(userModel.getUserIcon()).asBitmap().centerCrop().into(new BitmapImageViewTarget(mIcon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mIcon.setImageDrawable(circularBitmapDrawable);
                }
            });
            Glide.with(mContext).load(userModel.getUserIcon()).asBitmap().placeholder(R.mipmap.weibomovie_my_icon_head_default).into(new SimpleTarget(250, 250) {
                @Override
                public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
                    if ((Bitmap)resource != null) {
                        mIconBlur.setImageBitmap(new FastBlur().doBlur((Bitmap) resource, 8, false));
                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
