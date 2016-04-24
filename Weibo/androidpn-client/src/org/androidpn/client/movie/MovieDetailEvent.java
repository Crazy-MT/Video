package org.androidpn.client.movie;

/**
 * Created by MaoTong on 2016/4/18.
 * QQ:974291433
 */
public class MovieDetailEvent {
    private MovieModel movie ;

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
