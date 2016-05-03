package com.maotong.weibo.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.movie.pagelist.PageListModel;

import org.androidpn.client.Constants;
import org.androidpn.client.ServiceManager;
import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;

public class WeiBoApplication extends LitePalApplication {
    private static WeiBoApplication weiBoApplication;

    public static WeiBoApplication getInstance() {
        return weiBoApplication;
    }

    private String BASE_URL;
    private boolean isUser;
    private int userId;
    private SharedPreferences sp;
    private static final String REM_PW = "rem_pw";
    private static final String ACCOUNT = "account";
    private static final String PASSWORD = "password";
    protected static final String AUTO_LOGIN = "auto_login";
    private List<MovieModel> mHotShowingList;
    private List<MovieModel> mComingList;
    private List<PageListModel> mPageList;
    private List<List<MovieModel>> mPageListMovie;
    private List<String> mTags;
    private static final String TAGS = "tags";


    public List<String> getTags() {
        SharedPreferences sharedPrefs = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String tags = sharedPrefs.getString(TAGS, "科幻");
        String[] tagArr = tags.split("/");
        mTags = new ArrayList<>();

        for (String tag : tagArr) {
            if (!TextUtils.isEmpty(tag)) {
                mTags.add(tag.trim());
            }
        }
        return mTags;
    }

    public void setTags(String tags) {
        SharedPreferences sharedPrefs = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(TAGS, tags);
        editor.apply();
    }

    public List<PageListModel> getPageList() {
        return mPageList;
    }

    public void setPageList(List<PageListModel> mPageList) {
        this.mPageList = mPageList;
    }

    public List<List<MovieModel>> getPageListMovie() {
        return mPageListMovie;
    }

    public void setPageListMovie(List<List<MovieModel>> mPageListMovie) {
        this.mPageListMovie = mPageListMovie;
    }

    public List<MovieModel> getComingList() {
        return mComingList;
    }

    public void setComingList(List<MovieModel> mComingList) {
        this.mComingList = mComingList;
    }

    public List<MovieModel> getHotShowingList() {
        return mHotShowingList;
    }

    public void setHotShowingList(List<MovieModel> mHotShowingList) {
        this.mHotShowingList = mHotShowingList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        weiBoApplication = this;

        // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(org.androidpn.demoapp.R.drawable.notification);
        serviceManager.startService();
        serviceManager.setAlias("abc123456");

/*        List<String> tagsList = new ArrayList<String>();
        tagsList.add("sports");
        tagsList.add("music");*/
        serviceManager.setTags(getTags());

        //setBASE_URL("http://123.56.25.178:8080/WeiBoMovie/servlet"); //家
        setBASE_URL("http://192.168.0.104:8080/WeiBoMovie/servlet"); //tagux
        //setBASE_URL("http://192.168.1.113:8080/WeiBoMovie/servlet"); //家
//		setBASE_URL("http://192.168.10.212:8080/WeiBoMovie/servlet");  //racemind

        /*setUser(false);
        setUserId(0);

        //dUser = new d_User();
        sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sp.getBoolean(AUTO_LOGIN, true)) {
            //Toast.makeText(getApplicationContext(), sp.getBoolean(REM_PW, false)+"",1).show();
            //login();
        }*/


    }

    private void login() {
        new NewsAsyncTask().execute();
    }

    class NewsAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            return true;
            /*return new com.free.utils.JsonResolveUtils(getApplicationContext())
            .getLogin(sp.getString(ACCOUNT, ACCOUNT), sp.getString(PASSWORD, PASSWORD));*/
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                // Start the service
                /*ServiceManager serviceManager = new ServiceManager(
                        getApplicationContext());
				serviceManager.setNotificationIcon(R.drawable.ic_launcher);
				serviceManager.startService();
				serviceManager.setAlias("毛蛋");*/
            }
        }
    }


    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean isUser) {
        this.isUser = isUser;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
