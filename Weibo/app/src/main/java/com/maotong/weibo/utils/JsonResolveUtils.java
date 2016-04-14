package com.maotong.weibo.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.maotong.weibo.base.WeiBoApplication;
import com.maotong.weibo.movie.hotshowing.HotShowingModel;
import com.maotong.weibo.movie.pagelist.PageListModel;
import com.maotong.weibo.personal.UserModel;


public class JsonResolveUtils {
    @SuppressWarnings("unused")
    private Context context;

    public JsonResolveUtils(Context context) {
        this.context = context;
    }

    public JsonResolveUtils() {
    }

    public List<HotShowingModel> getPageListMovie(int pageListId) {
        List<HotShowingModel> movies = new ArrayList<>();
        HotShowingModel movie;
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
                    movie = new HotShowingModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"),0);

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

    public List<HotShowingModel> getMovie() {
        List<HotShowingModel> movies = new ArrayList<HotShowingModel>();
        HotShowingModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieServlet";

        SyncHttp syncHttp = new SyncHttp();

        try {
            json = syncHttp.httpGet(url, null);
            JSONObject jsonObject = new JSONObject(json);
            // 获取返回码
            ret = jsonObject.getString("ret").equals("success");

            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new HotShowingModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"),0);

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<HotShowingModel> getComing() {
        List<HotShowingModel> movies = new ArrayList<HotShowingModel>();
        HotShowingModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/ComingServlet";

        SyncHttp syncHttp = new SyncHttp();

        try {
            json = syncHttp.httpGet(url, null);
            JSONObject jsonObject = new JSONObject(json);
            // 获取返回码
            ret = jsonObject.getString("ret").equals("success");

            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("coming");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new HotShowingModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), rs.getString("release_date"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"));
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
        parameters.add(new Parameter("weibo_id" , userModel.getWeibo_id()+""));
        parameters.add(new Parameter("user_name" , userModel.getUserName()));
        parameters.add(new Parameter("icon" , userModel.getUserIcon()));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret ;
        try {
            json = syncHttp.httpPost(url , parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setLikeMovie(String uid, int movieId) {
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/LikeMovieServlet";
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id" , uid));
        parameters.add(new Parameter("movie_id" , movieId+""));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret ;
        try {
            json = syncHttp.httpPost(url , parameters);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret){
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
        parameters.add(new Parameter("weibo_id" , userModel.getWeibo_id()+""));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret ;
        try {
            json = syncHttp.httpPost(url , parameters);
            Log.e("tag", "logout: json" + json);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");
            if (ret){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HotShowingModel> getMovie(String uid) {
        List<HotShowingModel> movies = new ArrayList<HotShowingModel>();
        HotShowingModel movie;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASE_URL()
                + "/MovieServlet";

        SyncHttp syncHttp = new SyncHttp();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("weibo_id" , uid+""));
        try {
            json = syncHttp.httpPost(url, parameters);
            JSONObject jsonObject = new JSONObject(json);
            // 获取返回码
            ret = jsonObject.getString("ret").equals("success");
            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                JSONArray objectArr = dataObject.getJSONArray("movie");

                JSONObject rs = null;
                for (int i = 0; i < objectArr.length(); i++) {
                    rs = (JSONObject) objectArr.opt(i);
                    movie = new HotShowingModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"),rs.getInt("is_Like"));

                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * 用户登陆
     *
     * @return
     */
	/*public Boolean getLogin(String account, String password) {
		d_User dUser = new d_User();
		String url = WeiBoApplication.getWeiBoApplication().getBASE_URL()
				+ "/freemeal/Login";
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("username", account));
		params.add(new Parameter("password", password));

		SyncHttp syncHttp = new SyncHttp();
		String json = null;

		boolean ret = false;
		// 获取返回码
		try {
			json = syncHttp.httpPost(url, params);
			JSONObject jsonObject = new JSONObject(json);
			ret = jsonObject.getString("result").equals("success");

			if (ret) {
				JSONObject dataObject = jsonObject.getJSONObject("data");
				JSONObject rs = null;
				JSONArray objectArr = dataObject.getJSONArray("user");
				for (int i = 0; i < objectArr.length(); i++) {
					rs = (JSONObject) objectArr.opt(i);
					dUser = new d_User(rs.getInt("id"),
							rs.getString("username"), "我是密码",
							rs.getString("nickname"), rs.getString("sex"),
							rs.getString("email"), rs.getString("face"),
							rs.getString("regtime"), rs.getInt("logincounts"),
							rs.getString("lastlogin"),
							rs.getInt("loginstatus"), rs.getInt("joincounts"));
				}

				WeiBoApplication.getWeiBoApplication().setdUser(dUser);
				WeiBoApplication.getWeiBoApplication().setUser(true);
			}
			return jsonObject.getString("result").equals("success");
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}

*/

}
