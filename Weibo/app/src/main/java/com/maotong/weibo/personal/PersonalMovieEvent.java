package com.maotong.weibo.personal;

import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class PersonalMovieEvent {

    private List<MovieModel> movieList;
    private List<MovieModel> movieCommentList;

    public PersonalMovieEvent(List<MovieModel> movieList , List<MovieModel> movieCommentList) {
        this.movieCommentList = movieCommentList;
        this.movieList = movieList;
    }

    public List<MovieModel> getMovieCommentList() {
        return movieCommentList;
    }

    public void setMovieCommentList(List<MovieModel> movieCommentList) {
        this.movieCommentList = movieCommentList;
    }

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
    }
}
