package com.taguxdesign.maotong.myvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class NewVideoDetailActivity extends AppCompatActivity {

    public static final String HREF = "video_detail_href";
    public static final String URL = "video_detail_url";
    private WebView wv;
    private EditText tvDownloadHref;
    private String mHref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_video_detail);

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
        tvDownloadHref = (EditText) findViewById(R.id.id_text_download_href);
        wv.loadUrl(mHref);
    }

    private void getHref() {
        Bundle bundle = getIntent().getBundleExtra(URL);
        mHref = bundle.getString(HREF);
    }

}
