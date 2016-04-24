package com.weibomovie.weiboapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.weibomovie.dao.MovieDao;
import com.weibomovie.model.Movie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MovieData {

	public void httpPost() throws Exception {
		String url = "http://ting.weibo.com/movieapp/rank/hot ";
		String param = "v=1.4.0&d_n=Redmi+Note+3&wm=44994_0090&ip=172.17.136.2&count=30&os_n=Android&from=8614095010&page=1&aid=01Aory79fGVqEV8p41Azmn0X2A52y95PVeCpUlym0t6dMcuxo.&os_v=5.0.2&";
		StringBuilder stringBuilder = new StringBuilder();
		PrintWriter out = null;
		URL oracle;
		Movie movie;
		List<Movie> movies = new ArrayList<Movie>();
		try {
			oracle = new URL(url);
			URLConnection conn = oracle.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream() , "UTF8") );
			String inputLine = null;
			while ((inputLine = br.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			br.close();
			String str = stringBuilder.toString();
			StringBuffer stringBuffer = new StringBuffer(str);
			
			
			JSONObject jsonobject = JSONObject.fromObject(stringBuffer
					.toString());
			JSONObject jsonobject1 = (JSONObject) jsonobject.get("data");
			JSONArray objectArr = jsonobject1.getJSONArray("ranklist_hot");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
				movie = new Movie(rs.getInt("film_id"),rs.getString("name"), rs.getString("genre"),
						rs.getString("intro"), rs.getString("poster_url"),
						rs.getString("large_poster_url"), Float.parseFloat(rs
								.getString("score")), rs.getInt("score_count") , 1 , 0 , "");
				movies.add(movie);
			}
			saveData(movies);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private void saveData(List<Movie> movies) {
		MovieDao movieDao = new MovieDao();
		try {
			movieDao.saveDatas(movies);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
