package com.weibomovie.weiboapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weibomovie.dao.MovieDao;
import com.weibomovie.dao.PageListDao;
import com.weibomovie.model.Movie;
import com.weibomovie.model.Pagelist;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PageListData {

	public void httpPost() throws Exception {
		String url = "http://ting.weibo.com/movieapp/pagelist/recommend";
		String param = "v=1.4.0&d_n=Redmi+Note+3&wm=44994_0090&ip=172.27.35.2&count=10&os_n=Android&from=8614095010&page=1&aid=01Aory79fGVqEV8p41Azmn0X0IiB3R_h4eTzSETqj25q-Ag7Q.&os_v=5.0.2&";
		StringBuilder stringBuilder = new StringBuilder();
		PrintWriter out = null;
		URL oracle;
		Pagelist pageList;
		List<Pagelist> pageLists = new ArrayList<Pagelist>();
		try {
			oracle = new URL(url);
			URLConnection conn = oracle.openConnection(); 
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); 
			conn.setDoOutput(true);
			conn.setDoInput(true); 
			out = new PrintWriter(conn.getOutputStream()); 
			out.print(param); 
			out.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
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
			JSONArray objectArr = jsonobject1.getJSONArray("list");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
				pageList = new Pagelist(rs.getInt("pagelist_id"),
						rs.getString("name"), "", "", rs.getInt("movie_count"));
				pageLists.add(pageList);
			}
			saveData(pageLists);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	private void saveData(List<Pagelist> pagelists) { 
		PageListDao pagelistDao = new PageListDao();
		try {
			pagelistDao.saveDatas(pagelists);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

	/**
	 * 遍历每一个pagelist表的id字段，将id字段拼接到parameter里，post出去。
	 */
	public void getPageListMovie() {
		PageListDao pageListDao = new PageListDao();

		List<Integer> idList = null;
		try {
			idList = pageListDao.getPageListId();
		} catch (Exception e) { 
			e.printStackTrace();
		}

		for (int i = 0; i < idList.size(); i++) {
			getPageListMoviePost(idList.get(i));
		}

	}

	private void getPageListMoviePost(Integer integer) {
		StringBuilder stringBuilder = new StringBuilder();
		String url = "http://ting.weibo.com/movieapp/pagelist/recommendmovie";
		String param = "v=1.4.0&d_n=Redmi+Note+3&id="
				+ integer
				+ "&wm=44994_0090&ip=172.27.35.2&count=10&os_n=Android&from=8614095010&page=1&aid=01Aory79fGVqEV8p41Azmn0X0IiB3R_h4eTzSETqj25q-Ag7Q.&os_v=5.0.2& ";
		PrintWriter out = null;
		URL oracle;
		Movie movie = null;
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
					conn.getInputStream()));
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

			// 根据pagelist_id更新pagelist表
			Pagelist pageList1 = new Pagelist();
			pageList1.setId(integer);
			pageList1.setDescription(jsonobject1.getString("description"));
			pageList1.setCover_url(jsonobject1.getString("cover_url"));
			PageListDao pageListDao = new PageListDao();
			pageListDao.update(pageList1);

			// 更新movie表、或者插入
			JSONArray objectArr = jsonobject1.getJSONArray("list");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
 
				movie = new Movie(rs.getInt("film_id"), rs.getString("name"),
					 rs.getString("poster_url"),rs.getInt("score"), integer  );
				movies.add(movie);
			}
			saveMovie(movies);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 保存从pagelist里面读取到的电影信息
	 * 
	 * @param movies
	 */
	private void saveMovie(List<Movie> movies) { 
		MovieDao movieDao = new MovieDao();
		try { 
			movieDao.saveMovieFromPagelist(movies);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

}
