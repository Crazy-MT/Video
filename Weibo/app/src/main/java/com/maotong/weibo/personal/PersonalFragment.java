package com.maotong.weibo.personal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.api.Constants;
import com.maotong.weibo.api.WBAuthActivity;
import com.maotong.weibo.main.MainActivity;
import com.maotong.weibo.utils.FastBlur;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.sina.weibo.sdk.openapi.legacy.UsersAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class PersonalFragment extends android.support.v4.app.Fragment {

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;
    private Context mContext;
    private IUserApi mIUserApi = new IUserApi();
    private TextView mUserName;
    private ImageView mIconBlur, mIcon;
    private UserModel userModel;
    private boolean isLogin;
    private Button mLogout;

    /** 登出操作对应的listener */
    private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mContext = getContext();
    }

    @Subscribe
    public void onEventMainThread(final String userName) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LogoutAPI(mAccessToken).logout(mLogoutListener);
            }
        });
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
                        Toast.makeText( mContext ,mContext.getString(R.string.weibosdk_demo_logout_success) , Toast.LENGTH_LONG).show();
                        isLogin = false;
                        //清空界面信息
                        mIcon.setImageResource(R.mipmap.weibomovie_my_icon_head_default);
                        mIconBlur.setImageResource(R.mipmap.weibomovie_my_icon_head_default);
                        mUserName.setText("请登录");
                        //注销信息发送后台
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isLogout = new JsonResolveUtils(mContext).logout(userModel);
                                if (isLogout){
                                    Log.e("tag", "run: 注销成功" );
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
            Toast.makeText( mContext ,mContext.getString(R.string.weibosdk_demo_logout_failed) , Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(WeiboException e) {
            Toast.makeText( mContext ,mContext.getString(R.string.weibosdk_demo_logout_failed) , Toast.LENGTH_LONG).show();
        }
    }

    private void initView(View view) {
        mUserName = (TextView) view.findViewById(R.id.id_personal_user_name);
        mIconBlur = (ImageView) view.findViewById(R.id.id_personal_user_icon_blur);
        mIcon = (ImageView) view.findViewById(R.id.id_personal_user_icon);
        mLogout = (Button) view.findViewById(R.id.id_personal_logout);

        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，
        // 第一次启动本应用，AccessToken 不可用
        mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            isLogin = true;
        } else {
            mLogout.setVisibility(View.GONE);
        }

        view.findViewById(R.id.id_personal_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin) {
                    ((MainActivity) getActivity()).Auth();
                } else {
                    Toast.makeText(mContext, "已登录", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (isLogin) {
            new UsersAPI(mAccessToken).show(Long.valueOf(mAccessToken.getUid()), mIUserApi);
            mLogout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(mContext, R.string.weibosdk_demo_access_token_is_empty, Toast.LENGTH_LONG).show();
            mLogout.setVisibility(View.GONE);
        }
    }

    private void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            isLogin = true;
            new UsersAPI(mAccessToken).show(Long.valueOf(mAccessToken.getUid()), mIUserApi);
            mLogout.setVisibility(View.VISIBLE);
        }
    }

    private class IUserApi implements RequestListener {

        @Override
        public void onComplete(String response) {
            setUser(response);
            setUpIcon();
        }

        private void setUpIcon() {
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

            Glide.with(mContext).load(userModel.getUserIcon()).asBitmap().into(new SimpleTarget(250, 250) {

                @Override
                public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
                    mIconBlur.setImageBitmap(new FastBlur().doBlur((Bitmap) resource, 8, false));
                }
            });
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
                        Toast.makeText(mContext, "后台记录失败", Toast.LENGTH_LONG).show();
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
            Log.e("tag", "onComplete: user" + e.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("tag", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

}
