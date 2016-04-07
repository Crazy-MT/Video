package com.maotong.weibo.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maotong.weibo.R;

/**
 * Created by MaoTong on 2016/4/6.
 * QQ:974291433
 */
public class PersonalFragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container , false);
    }
}
