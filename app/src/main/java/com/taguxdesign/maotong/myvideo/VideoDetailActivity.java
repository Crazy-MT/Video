package com.taguxdesign.maotong.myvideo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.taguxdesign.maotong.myvideo.movie.DownloadHref;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String TAG = "VideoDetailActivity";
    public static final String HREF = "video_detail_href";
    public static final String URL = "video_detail_url";
    private WebView wv;
    private TextView tvDownloadHref;
    private String mHref;
    private PopupWindow mPopupWindow ;
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

        View popupView = getLayoutInflater().inflate(R.layout.window_href , null);
         mPopupWindow = new PopupWindow(popupView , WindowManager.LayoutParams
                .WRAP_CONTENT , WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources() ,(Bitmap) null));
        mPopupWindow.setWidth(measureWidth());

        Button copyButton = (Button) popupView.findViewById(R.id.id_window_copy);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyHref();
            }
        });

        Button downButton = (Button) popupView.findViewById(R.id.id_window_download);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoad();
            }
        });

        tvDownloadHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPopupWindow.isShowing()){
                    mPopupWindow.showAtLocation(tvDownloadHref , Gravity.NO_GRAVITY ,
                            0 , getPopUpWindowY());
                }
            }
        });
    }

    private int measureWidth() {
        int screenHeight = 0;
        WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenHeight = outMetrics.widthPixels;
        return screenHeight;
    }

    private int getPopUpWindowY() {
        int screenHeight = 0;
        WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenHeight = outMetrics.heightPixels;
        return screenHeight ;
    }


    private void copyHref(){
        ClipData clipData = ClipData.newPlainText("href" , tvDownloadHref.getText().toString());
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(VideoDetailActivity.this, "已复制到剪切板", Toast.LENGTH_SHORT).show();
    }

    private void downLoad(){
        Intent i = new Intent(Intent.ACTION_VIEW , Uri.parse(tvDownloadHref.getText().toString()));
        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
        }
    }

    private void getHref() {
        Bundle bundle = getIntent().getBundleExtra(URL);
        mHref = bundle.getString(HREF);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
