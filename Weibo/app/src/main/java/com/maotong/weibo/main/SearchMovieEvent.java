package com.maotong.weibo.main;

import com.maotong.weibo.movie.hotshowing.HotShowingModel;

/**
 * Created by MaoTong on 2016/4/19.
 * QQ:974291433
 */
public class SearchMovieEvent {

    private HotShowingModel movie ;


    public SearchMovieEvent(HotShowingModel movie) {
        this.movie = movie;
    }

    public HotShowingModel getMovie() {
        return movie;
    }

    public void setMovie(HotShowingModel movie) {
        this.movie = movie;
    }
}
