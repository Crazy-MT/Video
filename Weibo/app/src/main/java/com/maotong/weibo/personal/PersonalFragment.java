package com.maotong.weibo.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maotong.weibo.R;

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

    private TextView mUserNameText;
    private static final String MY_SERVLET = "http://192.168.1.109:8080/WeiBoMovie/servlet/UserServlet";

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
                mUserNameText.setText(userName);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        mUserNameText = (TextView) view.findViewById(R.id.id_user_name_text);
        initData();
        return view;
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(MY_SERVLET).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                EventBus.getDefault().post(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                EventBus.getDefault().post(responseStr);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
