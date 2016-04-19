package com.maotong.weibo.utils;

/**
 * Created by MaoTong on 2016/4/19.
 * QQ:974291433
 */
public class Log {
    private static final String TAG = "MT";
    private static final boolean LOG = true;
    public static void i(String msg) {
        if (LOG)
            android.util.Log.i(TAG , msg);
    }
    public static void d(String msg) {
        if (LOG)
            android.util.Log.d(TAG , msg);
    }
    public static void w(String msg) {
        if (LOG)
            android.util.Log.w(TAG , msg);
    }
    public static void w(String msg, Throwable throwable) {
        if (LOG)
            android.util.Log.w(TAG , msg, throwable);
    }
    public static void v(String msg) {
        if (LOG)
            android.util.Log.v(TAG , msg);
    }
    public static void e(String msg) {
        android.util.Log.e(TAG , msg);
    }
    public static void e(String msg, Throwable throwable) {
        android.util.Log.e(TAG , msg, throwable);
    }
}
