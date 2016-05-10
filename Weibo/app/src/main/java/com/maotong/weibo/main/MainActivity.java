package com.maotong.weibo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.api.Constants;
import com.maotong.weibo.movie.MovieTwoFragment;
import com.maotong.weibo.personal.LoginStatusEvent;
import com.maotong.weibo.personal.PersonalFragment;
import com.maotong.weibo.review.ReviewFragment;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PERSONALFRAGMENT = "personal_fragment";
    private static final String MOVIEONEFRAGMENT = "movie_fragment";
    private Button mBottomMovieButton, mBottomReviewButton, mBottomPersonalButton;
    private Context mContext;
    private MovieTwoFragment mMovieOneFragment;
    private ReviewFragment mReviewFragment;
    private PersonalFragment mPersonalFragment;
    private SsoHandler mSsoHandler;
    /**
     * 微博 Web 授权类，提供登陆等功能
     */
    private WeiboAuth mWeiboAuth;
    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initViews();
        initEvents();
        setDefaultFragment();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setDefaultFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mMovieOneFragment = new MovieTwoFragment();
        mReviewFragment = new ReviewFragment();
        mPersonalFragment = new PersonalFragment();
        transaction.add(R.id.id_content, mMovieOneFragment, MOVIEONEFRAGMENT);
        /*transaction.add(R.id.id_content, mReviewFragment);
        transaction.add(R.id.id_content, mPersonalFragment, PERSONALFRAGMENT);
        transaction.hide(mReviewFragment);
        transaction.hide(mPersonalFragment);*/
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(mReviewFragment);
        transaction.hide(mPersonalFragment);
        transaction.commit();*/
    }

    private void initEvents() {
        mBottomMovieButton.setOnClickListener(this);
        mBottomReviewButton.setOnClickListener(this);
        mBottomPersonalButton.setOnClickListener(this);
    }

    private void initViews() {
        mBottomMovieButton = (Button) findViewById(R.id.id_movie_btn);
        mBottomReviewButton = (Button) findViewById(R.id.id_review_btn);
        mBottomPersonalButton = (Button) findViewById(R.id.id_personal_btn);
        changeDrawableTop(true, false, false);
    }

    @Override
    public void onClick(View v) {


        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.id_movie_btn:
                changeDrawableTop(true, false, false);
                if (mMovieOneFragment == null) {
                    mMovieOneFragment = new MovieTwoFragment();
                }
                transaction.replace(R.id.id_content, mMovieOneFragment);
/*                transaction.hide(mReviewFragment);
                transaction.hide(mPersonalFragment);
                transaction.show(mMovieOneFragment);*/
                break;
            case R.id.id_review_btn:
                changeDrawableTop(false, true, false);
                if (mReviewFragment == null) {
                    mReviewFragment = new ReviewFragment();
                }
                transaction.replace(R.id.id_content, mReviewFragment);
          /*      transaction.hide(mMovieOneFragment);
                transaction.hide(mPersonalFragment);
                transaction.show(mReviewFragment);*/
                break;
            case R.id.id_personal_btn:
                changeDrawableTop(false, false, true);
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                }
                transaction.replace(R.id.id_content, mPersonalFragment , PERSONALFRAGMENT);
               /* transaction.hide(mReviewFragment);
                transaction.hide(mMovieOneFragment);
                transaction.show(mPersonalFragment);*/
                break;
            default:
                break;
        }

        transaction.commit();
    }

    /**
     * 改变底部按钮的top图片
     *
     * @param isMovieClicked
     * @param isReviewClicked
     * @param isPersonalClicked
     */
    private void changeDrawableTop(boolean isMovieClicked, boolean isReviewClicked, boolean isPersonalClicked) {
        Drawable drawableMovie = null;
        Drawable drawableReview = null;
        Drawable drawablePersonal = null;

        drawableMovie = isMovieClicked ? getResources().getDrawable(R.drawable.bottom_main_movie_s) : getResources().getDrawable(R.drawable.bottom_main_movie);
        if (drawableMovie != null) {
            drawableMovie.setBounds(0, 0, drawableMovie.getMinimumWidth(), drawableMovie.getMinimumHeight());
        }

        drawableReview = isReviewClicked ? getResources().getDrawable(R.drawable.bottom_main_review_s) : getResources().getDrawable(R.drawable.bottom_main_review);
        if (drawableReview != null) {
            drawableReview.setBounds(0, 0, drawableReview.getMinimumWidth(), drawableReview.getMinimumHeight());
        }

        drawablePersonal = isPersonalClicked ? getResources().getDrawable(R.drawable.bottom_main_personal_s) : getResources().getDrawable(R.drawable.bottom_main_personal);
        if (drawablePersonal != null) {
            drawablePersonal.setBounds(0, 0, drawablePersonal.getMinimumWidth(), drawablePersonal.getMinimumHeight());
        }

        mBottomPersonalButton.setCompoundDrawables(null, drawablePersonal, null, null);
        mBottomMovieButton.setCompoundDrawables(null, drawableMovie, null, null);
        mBottomReviewButton.setCompoundDrawables(null, drawableReview, null, null);
    }


    public void Auth() {
        // 创建微博实例
        mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        mSsoHandler = new SsoHandler(this, mWeiboAuth);
        mSsoHandler.authorize(new AuthListener());
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResult
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            Fragment personalFragment = getSupportFragmentManager().findFragmentByTag(PERSONALFRAGMENT);
            Fragment movieFragment = getSupportFragmentManager().findFragmentByTag(MOVIEONEFRAGMENT);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {

                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(mContext, mAccessToken);
//                transaction.detach(movieFragment);
//                transaction.attach(movieFragment);
//                transaction.commit();


                Toast.makeText(mContext, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("complete", mAccessToken.toString());
                personalFragment.onActivityResult(1, 0, intent);
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                EventBus.getDefault().post(new LoginStatusEvent(false, "", LoginStatusEvent.LOGIN_ERROR));
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(mContext,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(mContext,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 双击返回键退出
     */
    private boolean mIsExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
