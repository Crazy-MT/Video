package com.maotong.weibo.personal;

import com.maotong.weibo.movie.hotshowing.HotShowingModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class PersonalLikeMovieEvent {

    private List<HotShowingModel> movieList;

    public PersonalLikeMovieEvent(List<HotShowingModel> movieList) {
        this.movieList = movieList;
    }

    public List<HotShowingModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<HotShowingModel> movieList) {
        this.movieList = movieList;
    }
}
