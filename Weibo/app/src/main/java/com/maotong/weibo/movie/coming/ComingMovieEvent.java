package com.maotong.weibo.movie.coming;

import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class ComingMovieEvent {
    private List<MovieModel> movieList;

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
    }

    public ComingMovieEvent(List<MovieModel> movieList) {
        this.movieList = movieList;
    }
}
