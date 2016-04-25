package org.androidpn.client.movie;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonResolveUtils {
    @SuppressWarnings("unused")
    private Context context;

    public JsonResolveUtils(Context context) {
        this.context = context;
    }

    public JsonResolveUtils() {
    }

    public MovieModel getSearchMovie(String query) {
        MovieModel movie = null;
        String json;
        boolean ret = false;
        String url = "http://192.168.0.104:8080/WeiBoMovie/servlet/MovieSearchServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("name", query + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONObject movieObject = dataObject.getJSONObject("movie");

                movie = new MovieModel(movieObject.getInt("id"), movieObject.getString("name"), movieObject.getString("genre"),
                        movieObject.getString("intro"), movieObject.getString("poster_url"),
                        movieObject.getString("large_poster_url"), movieObject.getString("release_date"), (float) movieObject.getDouble("score"),
                        movieObject.getInt("score_count"), movieObject.getInt("is_Like"));
                movie.setVideo_url(movieObject.getString("video_url"));

                movie.setActors(dataObject.getString("actors"));
                movie.setDirector(dataObject.getString("director"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

}
