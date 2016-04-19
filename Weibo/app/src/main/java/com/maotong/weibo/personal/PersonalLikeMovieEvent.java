package com.maotong.weibo.personal;

import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class PersonalLikeMovieEvent {

    private List<MovieModel> movieList;

    public PersonalLikeMovieEvent(List<MovieModel> movieList) {
        this.movieList = movieList;
    }

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
    }
}
