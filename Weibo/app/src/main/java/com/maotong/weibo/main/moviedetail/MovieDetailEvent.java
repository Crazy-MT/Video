package com.maotong.weibo.main.moviedetail;

import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/18.
 * QQ:974291433
 */
public class MovieDetailEvent {
    private MovieModel movie ;
    private List<Actor> mActorList;
    private List<Comment> mCommentList;

    public List<Actor> getActorList() {
        return mActorList;
    }

    public void setActorList(List<Actor> actorList) {
        mActorList = actorList;
    }

    public MovieDetailEvent(MovieModel movie, List<Actor> actorList , List<Comment> commentList) {
        this.movie = movie;
        mActorList = actorList;
        this.mCommentList = commentList;

    }

    public List<Comment> getmCommentList() {
        return mCommentList;
    }

    public void setmCommentList(List<Comment> mCommentList) {
        this.mCommentList = mCommentList;
    }

    public List<Actor> getmActorList() {
        return mActorList;
    }

    public void setmActorList(List<Actor> mActorList) {
        this.mActorList = mActorList;
    }

    public MovieDetailEvent(MovieModel movie) {
        this.movie = movie;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieDetailEvent{" +
                "movie=" + movie +
                '}';
    }
}
