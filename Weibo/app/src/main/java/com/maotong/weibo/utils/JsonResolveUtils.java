package com.maotong.weibo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;

import com.maotong.weibo.base.WeiBoApplication;
import com.maotong.weibo.movie.coming.ComingModel;
import com.maotong.weibo.movie.hotshowing.HotShowingModel;
import com.maotong.weibo.movie.pagelist.PageListModel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
        String url = WeiBoApplication.getInstance().getBASEURL()
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
                            rs.getInt("score_count"));

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
        String url = WeiBoApplication.getInstance().getBASEURL()
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
        String url = WeiBoApplication.getInstance().getBASEURL()
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
                            rs.getInt("score_count"));
                    Log.e("tag", "getMovie: movie" + movie.toString());
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "getMovie: e" + e.toString());
        }
        return movies;
    }

    public List<ComingModel> getComing() {
        List<ComingModel> comingModels = new ArrayList<ComingModel>();
        ComingModel coming;
        String json;
        boolean ret = false;
        String url = WeiBoApplication.getInstance().getBASEURL()
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
                    coming = new ComingModel(rs.getInt("id"), rs.getString("name"), rs.getString("genre"),
                            rs.getString("intro"), rs.getString("poster_url"),
                            rs.getString("large_poster_url"), rs.getString("release_date"), (float) rs.getDouble("score"),
                            rs.getInt("score_count"), rs.getInt("wanttosee"), rs.getInt("is_wanttosee"));
                    comingModels.add(coming);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comingModels;
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    public Boolean setAddUser(String phone, String password) {
        /*String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
				+ "/freemeal/AddUser";
*/
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("username", phone));
        params.add(new Parameter("password", password));

        SyncHttp syncHttp = new SyncHttp();
        String json = null;

        // 获取返回码
        try {
            //json = syncHttp.httpPost(url, params);
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString("result").equals("success");
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    /**
     * 用户登陆
     *
     * @param account
     * @param password
     * @return
     */
	/*public Boolean getLogin(String account, String password) {
		d_User dUser = new d_User();
		String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
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
    /***
     * 得到全部的activity活动
     *
     * @return
     */
	/*public List<d_Activity> getActive() {
		List<d_Activity> activitys = new ArrayList<d_Activity>();
		d_Activity activity = new d_Activity();

		boolean ret = false;
		String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
				+ "/freemeal/GetAllActivity";
		SyncHttp syncHttp = new SyncHttp();
		String json = null;
		try {
			json = syncHttp.httpGet(url, null); 
			JSONObject jsonObject = new JSONObject(json);
			// 获取返回码
			ret = jsonObject.getString("result").equals("success");
			if (ret) {
				JSONObject dataObject = jsonObject.getJSONObject("data");
				JSONArray objectArr = dataObject.getJSONArray("Activity");

				JSONObject rs = null;
				for (int i = 0; i < objectArr.length(); i++) {
					rs = (JSONObject) objectArr.opt(i);
					*//*activity = new d_Activity(rs.getInt("id"),
							rs.getString("title"), rs.getString("imgpath"),
							rs.getInt("peocounts"), rs.getInt("curpeocounts"),
							rs.getInt("status"), rs.getString("pubtime"),
							rs.getString("starttime"), rs.getString("endtime"),
							rs.getString("businessinfo"),
							rs.getString("consumerinfo"),
							rs.getString("consumernotice"), rs.getInt("cid"),
							rs.getString("systemtime"));

					activitys.add(activity);*//*
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activitys;
	}*/

    /**
     * 点击参与 ， 参与人数加1
     *
     * @param id
     * @return
     */
    public int setActivity(int id) {
        String curpeocounts = "0";
		/*String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
				+ "/freemeal/GetCurpeocounts";*/

        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("id", id + ""));
		/*params.add(new Parameter("userid", WeiBoApplication.getWeiBoApplication()
				.getdUser().getId()
				+ ""));*/
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret = false;
        // 获取返回码
        try {
            //json = syncHttp.httpPost(url, params);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");

            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                curpeocounts = dataObject.getString("Curpeocounts");
            }
            return Integer.parseInt(curpeocounts);
        } catch (Exception e1) {
            e1.printStackTrace();
            return Integer.parseInt(curpeocounts);
        }
    }

    /**
     * 动态刷新 ， 得到参与人数
     *
     * @param id
     * @return
     */
    public int getActivityPeople(int id) {

        String curpeocounts = "0";

		/*String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
				+ "/freemeal/GetCurpeocountsNow";
*/
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("id", id + ""));

        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret = false;
        // 获取返回码
        try {
            //json = syncHttp.httpPost(url, params);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");

            if (ret) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                curpeocounts = dataObject.getString("CurpeocountsNow");
            }
            return Integer.parseInt(curpeocounts);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    /***
     * @return
     */
    public void getNotification() {
        String url = "http://192.168.31.158:8080/freemeal/Test";

        SyncHttp syncHttp = new SyncHttp();
        String json = null;

        try {
            json = syncHttp.httpGet(url, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void setPassWord(int id, String string) {

    }

    public boolean setSex(int id, String string) {
        //String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
        //		+ "/freemeal/EditUserSex";

        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("id", id + ""));
        params.add(new Parameter("sex", string));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret = false;
        // 获取返回码
        try {
            //	json = syncHttp.httpPost(url, params);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");

            if (ret) {
                Log.e("Json", ret + "");
                return true;
            } else {

                Log.e("Json", ret + "");
                return false;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean setNickName(int id, String string) {
        //String url = WeiBoApplication.getWeiBoApplication().getBASEURL()
        //		+ "/freemeal/EditUserNickname";

        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("id", id + ""));
        params.add(new Parameter("nickname", string));
        SyncHttp syncHttp = new SyncHttp();
        String json = null;
        boolean ret = false;
        // 获取返回码
        try {
            //	json = syncHttp.httpPost(url, params);
            JSONObject jsonObject = new JSONObject(json);
            ret = jsonObject.getString("result").equals("success");

            if (ret) {
                Log.e("Json", ret + "");
                return true;
            } else {

                Log.e("Json", ret + "");
                return false;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }


}
