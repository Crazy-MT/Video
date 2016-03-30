package com.taguxdesign.maotong.myvideo.movie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.taguxdesign.maotong.myvideo.R;

public class NewVideoDetailActivity extends AppCompatActivity {

    public static final String HREF = "video_detail_href";
    public static final String URL = "video_detail_url";
    private WebView wv;
    private TextView tvDownloadHref;
    private String mHref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moive_detail);

        getHref();

        initView();

        downloadHref();
    }

    private void downloadHref() {
        final String[] downloadHref = {""};
        new Thread(new Runnable() {
            @Override
            public void run() {
                downloadHref[0] = new DownloadHref(mHref).getDownloadHref();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvDownloadHref.setText(downloadHref[0]);
                    }
                });
            }


        }).start();

    }

    private void initView() {
        wv = (WebView) findViewById(R.id.id_web_href);
        tvDownloadHref = (TextView) findViewById(R.id.id_text_download_href);
        wv.loadUrl(mHref);

        tvDownloadHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " + tvDownloadHref.getText().toString());
                Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse(tvDownloadHref.getText().toString()));
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }

    private void getHref() {
        Bundle bundle = getIntent().getBundleExtra(URL);
        mHref = bundle.getString(HREF);
    }

}
