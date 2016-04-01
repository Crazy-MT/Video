package com.taguxdesign.maotong.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mTestImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init();
        mTestImageView = (ImageView) findViewById(R.id.id_img);
        mTestImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_img:
                //ObjectAnimator.ofFloat(v , "rotationX" , 0.0f , 360.f).setDuration(500).start();  //简单的旋转
                //rotateyAnimRun(v);
                //parabolaAnimRun(v);
                verticalRun(v);
                break;
        }
    }

    /**
     * 缩小的同时淡出
     *
     * @param view
     */
    public void rotateyAnimRun(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "mt", 1.0f, 0.0f).setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
                Logger.e(cVal + "");
            }
        });
    }

    /**
     * 抛物线
     *
     * @param view
     */
    public void parabolaAnimRun(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "mt", 0.0f, 1.0f).setDuration(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                view.setTranslationX(400 * cVal);
                view.setTranslationY(-400 * cVal * cVal);
            }
        });
    }

    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);

        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }

    /**
     * 垂直移动
     */
    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0 , 500);
        animator.setTarget(view);
        animator.setDuration(1000).start();
    }
}
