package com.maotong.weibo.movie.hotshowing;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class HotShowingModel {

    private String score;
    private String name;
    private String commentCount;
    private boolean isLike;
    private String movieBg ;

    public String getMovieBg() {
        return movieBg;
    }

    public void setMovieBg(String movieBg) {
        this.movieBg = movieBg;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public String toString() {
        return score + " " + name + " " + commentCount + " " + isLike;
    }
}
