package com.maotong.weibo.openapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.R;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by MaoTong on 2016/4/5.
 * QQ:974291433
 */
public class WBPublicAPIActivity extends Activity implements View.OnClickListener {

    public Button mPublicButton;
    public TextView mPublicTextView;
    private IPublicTimeLine mPublicTimeLine = new IPublicTimeLine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_public_timeline);

        mPublicButton = (Button) findViewById(R.id.open_api_public_btn);
        mPublicTextView = (TextView) findViewById(R.id.open_api_public_tv);

        mPublicButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(WBPublicAPIActivity.this);

        if (accessToken != null && accessToken.isSessionValid()){
            new StatusesAPI(accessToken).publicTimeline(50,1 , false,mPublicTimeLine);
        } else {
            Toast.makeText(getApplicationContext() , R.string.weibosdk_demo_access_token_is_empty , Toast.LENGTH_LONG).show();
        }

    }

    private class IPublicTimeLine implements RequestListener{

        @Override
        public void onComplete(String response) {
            mPublicTextView.setText(response);
        }

        @Override
        public void onComplete4binary(ByteArrayOutputStream responseOS) {
            mPublicTextView.setText(responseOS.toString());
        }

        @Override
        public void onIOException(IOException e) {
            mPublicTextView.setText(e.toString());
        }

        @Override
        public void onError(WeiboException e) {
           // mPublicTextView.setText(e.toString());
        }
    }
}
