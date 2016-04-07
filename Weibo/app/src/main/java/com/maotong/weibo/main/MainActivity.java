package com.maotong.weibo.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.maotong.weibo.R;
import com.maotong.weibo.movie.MovieOneFragment;
import com.maotong.weibo.movie.MovieTwoFragment;
import com.maotong.weibo.personal.PersonalFragment;
import com.maotong.weibo.review.ReviewFragment;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBottomMovieButton, mBottomReviewButton, mBottomPersonalButton;

    private MovieTwoFragment mMovieOneFragment;
    private ReviewFragment mReviewFragment;
    private PersonalFragment mPersonalFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mMovieOneFragment = new MovieTwoFragment();
        mReviewFragment = new ReviewFragment();
        mPersonalFragment = new PersonalFragment();
        transaction.add(R.id.id_content, mMovieOneFragment);
        transaction.add(R.id.id_content, mReviewFragment);
        transaction.add(R.id.id_content, mPersonalFragment);
        transaction.hide(mReviewFragment);
        transaction.hide(mPersonalFragment);
        transaction.commit();
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

    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.id_movie_btn:
                changeDrawableTop(true, false, false);
                if (mMovieOneFragment == null) {
                    mMovieOneFragment = new MovieTwoFragment();
                }
                transaction.hide(mReviewFragment);
                transaction.hide(mPersonalFragment);
                transaction.show(mMovieOneFragment);
                break;
            case R.id.id_review_btn:
                changeDrawableTop(false, true, false);
                if (mReviewFragment == null) {
                    mReviewFragment = new ReviewFragment();
                }
                transaction.hide(mMovieOneFragment);
                transaction.hide(mPersonalFragment);
                transaction.show(mReviewFragment);
                break;
            case R.id.id_personal_btn:
                changeDrawableTop(false, false, true);
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                }
                transaction.hide(mReviewFragment);
                transaction.hide(mMovieOneFragment);
                transaction.show(mPersonalFragment);
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

}
