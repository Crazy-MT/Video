package com.maotong.weibo.personal;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.maotong.weibo.R;
import com.maotong.weibo.utils.FastBlur;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
        initData();
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.blur);
        final ImageView imageView = (ImageView) view.findViewById(R.id.backdrop);
        imageView.setImageResource(R.mipmap.blur);
        imageView.setImageBitmap(new FastBlur().doBlur(bitmap , 8 , false));
        return view;
    }

    private void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
