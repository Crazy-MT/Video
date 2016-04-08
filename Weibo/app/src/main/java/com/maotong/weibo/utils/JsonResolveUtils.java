package com.maotong.weibo.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.maotong.weibo.base.MyApplication;


public class JsonResolveUtils {
	@SuppressWarnings("unused")
	private Context context;

	public JsonResolveUtils(Context context) {
		this.context = context;
	}
	
	public JsonResolveUtils( ) {
 
	}

	/**
	 * 用户注册
	 * @param phone
	 * @param password
	 * @return
	 */
	public Boolean setAddUser(String phone, String password) {
		String url = MyApplication.getMyApplication().getBASEURL()
				+ "/freemeal/AddUser";

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("username", phone));
		params.add(new Parameter("password", password)); 

		SyncHttp syncHttp = new SyncHttp();
		String json = null;

		// 获取返回码
		try {
			json = syncHttp.httpPost(url, params);
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
		String url = MyApplication.getMyApplication().getBASEURL()
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

				MyApplication.getMyApplication().setdUser(dUser);
				MyApplication.getMyApplication().setUser(true);
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
		String url = MyApplication.getMyApplication().getBASEURL()
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
		String url = MyApplication.getMyApplication().getBASEURL()
				+ "/freemeal/GetCurpeocounts";

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("id", id + ""));
		/*params.add(new Parameter("userid", MyApplication.getMyApplication()
				.getdUser().getId()
				+ ""));*/
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

		String url = MyApplication.getMyApplication().getBASEURL()
				+ "/freemeal/GetCurpeocountsNow";

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("id", id + ""));

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
				curpeocounts = dataObject.getString("CurpeocountsNow");
			}
			return Integer.parseInt(curpeocounts);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 0;
		}
	}

	/***
	 * 
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
		String url = MyApplication.getMyApplication().getBASEURL()
				+ "/freemeal/EditUserSex";

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("id", id + ""));
		params.add(new Parameter("sex", string));
		SyncHttp syncHttp = new SyncHttp();
		String json = null;
		boolean ret = false;
		// 获取返回码
		try {
			json = syncHttp.httpPost(url, params);
			JSONObject jsonObject = new JSONObject(json);
			ret = jsonObject.getString("result").equals("success");

			if (ret) {
				Log.e("Json", ret+"");
				return true ; 
			}else{

				Log.e("Json", ret+"");
				return false ; 
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false ; 
		}
	}

	public boolean setNickName(int id, String string) {
		String url = MyApplication.getMyApplication().getBASEURL()
				+ "/freemeal/EditUserNickname";

		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("id", id + ""));
		params.add(new Parameter("nickname", string));
		SyncHttp syncHttp = new SyncHttp();
		String json = null;
		boolean ret = false;
		// 获取返回码
		try {
			json = syncHttp.httpPost(url, params);
			JSONObject jsonObject = new JSONObject(json);
			ret = jsonObject.getString("result").equals("success");

			if (ret) {
				Log.e("Json", ret+"");
				return true ; 
			}else{

				Log.e("Json", ret+"");
				return false ; 
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false ; 
		}
	}
}
