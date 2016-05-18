package com.maotong.weibo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.maotong.weibo.base.WeiBoApplication;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.main.moviedetail.Actor;
import com.maotong.weibo.main.moviedetail.Comment;
import com.maotong.weibo.movie.pagelist.PageListModel;
import com.maotong.weibo.personal.UserModel;
import com.maotong.weibo.review.ReviewModel;

import org.json.JSONArray;
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

    public List<MovieModel> getPageListMovie(int pageListId) {
        List<MovieModel> movies = new ArrayList<>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/PageListMovieServlet";

        SyncHttp syncHttp = new SyncHttp();

        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("pagelistid", pageListId + ""));

        try {
            json = syncHttp.httpPost(url, params);

            JSONObject jsonObject = new JSONObject(json);
            // 获取返回码
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), 0);

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<PageListModel> getPageList() {
        List<PageListModel> pageLists = new ArrayList<PageListModel>();
        PageListModel pageList;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/PagelistServlet";

        SyncHttp syncHttp = new SyncHttp();

        try {
            json = syncHttp.httpGet(url, null);
            JSONObject jsonObject = new JSONObject(json);
            // 获取返回码
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("pagelist");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    pageList = new PageListModel(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                            rs.getString("cover_url"),
                            rs.getInt("movie_count"));

                    pageLists.add(pageList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageLists;
    }

    public List<MovieModel> getMovie() {
        //未登录时获取电影信息
        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieServlet";

        SyncHttp syncHttp = new SyncHttp();
        try {
            json = syncHttp.httpGet(url, null);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), 0);

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<MovieModel> getComing() {
        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/ComingServlet";

        SyncHttp syncHttp = new SyncHttp();

        try {
            json = syncHttp.httpGet(url, null);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");

            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("coming");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), rs.getString("release_date"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"));
                    movie.setIsLike(0);
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }


    public boolean setUser(UserModel userModel) {
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/UserServlet";
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", userModel.getWeibo_id() + ""));
        parameters.add(new Parameter("user_name", userModel.getUserName()));
        parameters.add(new Parameter("icon", userModel.getUserIcon()));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret;
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setLikeMovie(String uid, int movieId, boolean isLike) {
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/LikeMovieServlet";

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", uid));
        parameters.add(new Parameter("movie_id", movieId + ""));
        parameters.add(new Parameter("is_like", isLike + ""));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret;
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout(UserModel userModel) {
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/LogoutServlet";
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", userModel.getWeibo_id() + ""));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret;
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");

            if (ret) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MovieModel> getMovie(String uid) {
        //根据用户名获取电影信息
        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", uid + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), rs.getInt("is_Like"));

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<MovieModel> getMovieLike(String uid) {
        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/GetMovieLikeServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", uid + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), rs.getInt("is_Like"));

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String tags = "";
        for (MovieModel movieModel : movies) {
            if (!TextUtils.isEmpty(movieModel.getGenre())){
                tags += movieModel.getGenre();
                tags += "/";
            }
        }
        WeiBoApplication.getInstance().setTags(tags);
        return movies;

    }

    public List<MovieModel> getComing(String uid) {

        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/ComingServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id", uid + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);


                    movie = new MovieModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), rs.getString("release_date"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), rs.getInt("is_Like"));

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public MovieModel getMovieDetail(int id) {
        MovieModel movie = null;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieDetailServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("movie_id", id + ""));
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

    public MovieModel getSearchMovie(String query) {
        MovieModel movie = null;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieSearchServlet";

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

    public List<Actor> getActor(int mMovieId) {
        List<Actor> actorList = new ArrayList<>();
        Actor actor = null;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/ActorsServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("movie_id", mMovieId + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray actorObject = dataObject.getJSONArray("actor");
                for (int i = 0; i< actorObject.length(); i++){
                    JSONObject rs = (JSONObject) actorObject.opt(i);
                    actor = new Actor(rs.getInt("id"),rs.getString("name") ,
                            rs.getString("url"));
                    actorList.add(actor);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actorList;
    }

    public List<Comment> getComment(int id) {

        List<Comment> commentList = new ArrayList<>();
        Comment comment = null;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/CommentServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter("movie_id", id + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray actorObject = dataObject.getJSONArray("comment");
                for (int i = 0; i< actorObject.length(); i++){
                    JSONObject rs = (JSONObject) actorObject.opt(i);
                    comment = new Comment(rs.getString("text") , rs.getString("userName") , rs.getString("userIcon"));
                    commentList.add(comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commentList;
    }

    public List<MovieModel> getCommentMovieByUserId(String uid) {
        List<MovieModel> movies = new ArrayList<MovieModel>();
        MovieModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/CommentUserServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("userid", uid + ""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new MovieModel();
                    movie.setName(rs.getString("name"));
                    movie.setPoster_url(rs.getString("poster_url"));
                    movie.setId(rs.getInt("id"));
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return movies;
    }

    public List<ReviewModel> getComment() {
        List<ReviewModel> commentList = new ArrayList<>();
        ReviewModel comment = null;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/CommentServlet";

        SyncHttp syncHttp = new SyncHttp();
        try {
            json = syncHttp.httpGet(url , null);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray actorObject = dataObject.getJSONArray("comment");
                for (int i = 0; i< actorObject.length(); i++){
                    JSONObject rs = (JSONObject) actorObject.opt(i);
                    comment = new ReviewModel();
                    comment.setMovieName(rs.getString("movieName"));
                    comment.setText(rs.getString("text"));
                    comment.setUserIcon(rs.getString("userIcon"));
                    comment.setUserName(rs.getString("userName"));
                    commentList.add(comment);
                    Log.e("tag", "getComment: comment.toString()" + comment.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "getComment: comment.toString()" + e.toString());
        }

        return commentList;
    }
}
