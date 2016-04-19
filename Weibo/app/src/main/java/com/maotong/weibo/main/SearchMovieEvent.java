package com.maotong.weibo.main;

/**
 * Created by MaoTong on 2016/4/19.
 * QQ:974291433
 */
public class SearchMovieEvent {

    private MovieModel movie ;


    public SearchMovieEvent(MovieModel movie) {
        this.movie = movie;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }
}
