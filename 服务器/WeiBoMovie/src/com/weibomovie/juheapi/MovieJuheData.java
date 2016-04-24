package com.weibomovie.juheapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.weibomovie.dao.MovieDao;
import com.weibomovie.db.Constant;
import com.weibomovie.model.Movie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MovieJuheData {

	
	// 配置您申请的KEY
	public static final String APPKEY = "1d4b3c6407d1e43ce4ad503f18250dec";

	public void httpPost() throws Exception {
		String result = null;
		String url = "http://v.juhe.cn/movie/movies.today";// 请求接口地址
		Map params = new HashMap();// 请求参数
		params.put("cityid", "2");// 城市ID
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
		params.put("dtype", "");// 返回数据的格式,xml/json，默认json

		Movie movie;
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
            result =Constant.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		 /*
			JSONObject jsonobject = JSONObject.fromObject(stringBuffer
					.toString());
			JSONObject jsonobject1 = (JSONObject) jsonobject.get("data");
			JSONArray objectArr = jsonobject1.getJSONArray("ranklist_hot");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
				movie = new Movie(rs.getInt("film_id"), rs.getString("name"),
						rs.getString("genre"), rs.getString("intro"),
						rs.getString("poster_url"),
						rs.getString("large_poster_url"), Float.parseFloat(rs
								.getString("score")), rs.getInt("score_count"),
						1, 0, "");
				movies.add(movie);
			 
			saveData(movies);*/
		 
	}

	private void saveData(List<Movie> movies) {
		// TODO Auto-generated method stub
		MovieDao movieDao = new MovieDao();
		try {
			movieDao.saveDatas(movies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
