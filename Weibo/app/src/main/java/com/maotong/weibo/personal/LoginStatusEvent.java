package com.maotong.weibo.personal;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class LoginStatusEvent {

    public static String LOGOUT_SUCCESS = "logout_success";
    public static String LOGOUT_ERROR = "logout_error";
    public static String LOGIN_SUCCESS = "login_success";
    public static String LOGIN_ERROR = "login_error";
    private boolean mIsLogin;
    private String doLogout;
    private String doLogin;

    public String getDoLogin() {
        return doLogin;
    }

    public void setDoLogin(String doLogin) {
        this.doLogin = doLogin;
    }

    public LoginStatusEvent(boolean mIsLogin, String doLogout, String doLogin) {
        this.mIsLogin = mIsLogin;
        this.doLogout = doLogout;
        this.doLogin = doLogin;
    }

    public boolean ismIsLogin() {
        return mIsLogin;
    }

    public LoginStatusEvent(boolean mIsLogin, String doLogout) {
        this.mIsLogin = mIsLogin;
        this.doLogout = doLogout;
    }

    public String getDoLogout() {
        return doLogout;
    }

    public void setDoLogout(String doLogout) {
        this.doLogout = doLogout;
    }

    public void setmIsLogin(boolean mIsLogin) {
        this.mIsLogin = mIsLogin;
    }

    public LoginStatusEvent() {
    }

    public LoginStatusEvent(boolean mIsLogin) {
        this.mIsLogin = mIsLogin;
    }
}
