package com.weibomovie.weiboapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.weibomovie.dao.ActorDao;
import com.weibomovie.dao.DirectorDao;
import com.weibomovie.dao.MovieDao;
import com.weibomovie.model.Actor;
import com.weibomovie.model.Director;
import com.weibomovie.model.Movie;

public class MovieDetailData {

	public String getMovieDetail(int movie_id) {
		StringBuffer stringBuffer = null ; 
		String url = " http://ting.weibo.com/movieapp/page/base";
		String param = "v=1.4.0&d_n=Redmi+Note+3&wm=44994_0090&ip=172.27.35.2&os_n=Android&" +
				"film_id="+movie_id+"&from=8614095010" +
				"&aid=01Aory79fGVqEV8p41Azmn0X0IiB3R_h4eTzSETqj25q-Ag7Q." +
				"&os_v=5.0.2& ";
		StringBuilder stringBuilder = new StringBuilder();
		PrintWriter out = null;
		URL oracle;
		Movie movie = new Movie();
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
			stringBuffer = new StringBuffer(str);
			
			JSONObject jsonobject = JSONObject.fromObject(stringBuffer
					.toString());
			JSONObject jsonobjectData = (JSONObject) jsonobject.get("data");
			JSONObject jsonObjectBaseInfo = (JSONObject) jsonobjectData.get("base_info");
			String director = jsonObjectBaseInfo.getString("directors");
			//插入数据库 ， 存放导演名 和 电影名
			DirectorDao directorDao = new DirectorDao();
			directorDao.addDirector(new Director(director , movie_id));
			
			String actors = jsonObjectBaseInfo.getString("actors");
			//分离演员名 并且插入数据库
			String actorsArr[] = actors.split("/");
			List<Actor> actorList = new ArrayList<Actor>();
			for(String actor: actorsArr){
				System.out.println(actor);
				actorList.add(new Actor(actor , movie_id));
			}

			ActorDao actorDao = new ActorDao();
			actorDao.addActor(actorList);
			
			boolean release = jsonObjectBaseInfo.getBoolean("release");
			if(!release){
				movie.setIs_coming(1);
			}
			System.out.println(release); 
			JSONArray objectArr =( (JSONObject) jsonObjectBaseInfo.get("videos")).getJSONArray("list");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
				movie.setVideo_url(rs.getString("video_url"));
			}
			//保存movie
			System.out.println(movie.toString());
			movie.setId(movie_id);
			saveData(movie);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		} 
		return stringBuffer.toString(); 
	}

	private void saveData(Movie movie) throws SQLException {
		MovieDao movieDao = new MovieDao();
		movieDao.updateMovie(movie);
	}
}