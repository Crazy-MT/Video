package com.maotong.weibo.main.moviedetail;

/**
 * Created by Mao on 2016/5/8.
 */
public class Comment {

    private String userName;
    private String userIcon;
    private String commentText;
    private long userId;
    private int movieId;

    public Comment() {
    }

    public Comment(String commentText, String user_name, String user_icon) {
        this.commentText = commentText;
        this.userName = user_name;
        this.userIcon = user_icon;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
