package com.maotong.weibo.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.maotong.weibo.R;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBottomMovieButton, mBottomReviewButton, mBottomPersonalButton;

    private ContentFragment contentFragment ;
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
        contentFragment = new ContentFragment();
        transaction.replace(R.id.id_content , contentFragment);
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


    private void setupViewPager(ViewPager viewPager) {
        com.maotong.weibo.main.Adapter adapter = new com.maotong.weibo.main.Adapter(getSupportFragmentManager());
        //adapter.addFragment(new MovieFragment() , R.string.);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_movie_btn:
                changeDrawableTop(true, false, false);
                break;
            case R.id.id_review_btn:
                changeDrawableTop(false , true , false);
                break;
            case R.id.id_personal_btn:
                changeDrawableTop(false , false ,true);
                break;
            default:
                break;
        }
    }

    /**
     * 改变底部按钮的top图片
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
