package com.maotong.weibo.main;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.maotong.weibo.R;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView ;
    public static String INTENT_EXTRA = "video_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_video);
        String url = getIntent().getStringExtra("video_url");
        videoView = (VideoView) findViewById(R.id.id_movie_detail_video_view);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        videoView.requestFocus();

    }
}
