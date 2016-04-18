package com.maotong.weibo.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

public class WeiBoApplication extends Application {
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

    @Override
    public void onCreate() {
        super.onCreate();
        weiBoApplication = this;
        setBASE_URL("http://192.168.0.153:8080/WeiBoMovie/servlet"); //tagux
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
