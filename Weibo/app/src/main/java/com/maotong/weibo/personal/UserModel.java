package com.maotong.weibo.personal;

/**
 * Created by MaoTong on 2016/4/13.
 * QQ:974291433
 */
public class UserModel {

    private int id;
    private long weibo_id;
    private String userName;
    private String userIcon;

    public UserModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getWeibo_id() {
        return weibo_id;
    }

    public void setWeibo_id(long weibo_id) {
        this.weibo_id = weibo_id;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", weibo_id=" + weibo_id +
                ", userName='" + userName + '\'' +
                ", userIcon='" + userIcon + '\'' +
                '}';
    }

    public UserModel(int id, long weibo_id, String userName, String userIcon) {
        this.id = id;
        this.weibo_id = weibo_id;
        this.userName = userName;
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
