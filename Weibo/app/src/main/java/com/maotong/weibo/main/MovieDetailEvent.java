package com.maotong.weibo.main;

import com.maotong.weibo.movie.hotshowing.HotShowingModel;

/**
 * Created by MaoTong on 2016/4/18.
 * QQ:974291433
 */
public class MovieDetailEvent {
    private HotShowingModel movie ;

    public MovieDetailEvent(HotShowingModel movie) {
        this.movie = movie;
    }

    public HotShowingModel getMovie() {
        return movie;
    }

    public void setMovie(HotShowingModel movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieDetailEvent{" +
                "movie=" + movie +
                '}';
    }
}
