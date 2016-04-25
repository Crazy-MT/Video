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
import com.weibomovie.dao.ActorMovieDao;
import com.weibomovie.dao.DirectorDao;
import com.weibomovie.dao.DirectorMovieDao;
import com.weibomovie.dao.MovieDao;
import com.weibomovie.model.Actor;
import com.weibomovie.model.ActorMovie;
import com.weibomovie.model.Director;
import com.weibomovie.model.DirectorMovie;
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
			
			getDirectorNameAndSave(jsonObjectBaseInfo , movie_id);  
			getActorMovieNameAndSave(jsonObjectBaseInfo , movie_id);
			getMovieAndSave(jsonObjectBaseInfo , movie_id); 
			getActorAndSave(jsonobjectData );
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		} 
		return stringBuffer.toString(); 
	}

	private void getActorAndSave(JSONObject jsonobjectData) throws SQLException { 
		Actor actor = null;
		Director director = null;
		List<Actor> actors = new ArrayList<Actor>();
		List<Director> directors = new ArrayList<Director>();
		JSONObject creatorInfo = jsonobjectData.getJSONObject("creator_info");
		JSONObject actorsJson = creatorInfo.getJSONObject("actors");
		JSONArray list = actorsJson.getJSONArray("list");
		for(int i = 0 ; i < list.size(); ++i){
			JSONObject rs = list.getJSONObject(i);
			if("导演".equals(rs.getString("job"))){
				//存放导演数据到数据库
				director = new Director();
				director.setName(rs.getString("name"));
				director.setUrl(rs.getString("profile_image_url"));
				directors.add(director);
			} else if("演员".equals(rs.getString("job"))){
				actor = new Actor();
				//存放演员到数据库
				actor.setName(rs.getString("name"));
				actor.setUrl(rs.getString("profile_image_url")); 
				actors.add(actor);
			}
		} 
		
		ActorDao actorDao = new ActorDao();
		actorDao.addActor(actors);
		 
		DirectorDao directorDao = new DirectorDao();
		directorDao.addDirector(directors);
	}

	private void getMovieAndSave(JSONObject jsonObjectBaseInfo, int movie_id) throws SQLException { 
		Movie movie = new Movie();
		boolean release = jsonObjectBaseInfo.getBoolean("release");
		if(!release){
			movie.setIs_coming(1);
		}  
		if((JSONObject) jsonObjectBaseInfo.get("videos") != null){
			JSONArray objectArr =( (JSONObject) jsonObjectBaseInfo.get("videos")).getJSONArray("list");
			JSONObject rs = null;
			for (int i = 0; i < objectArr.size(); i++) {
				rs = (JSONObject) objectArr.opt(i);
				movie.setVideo_url(rs.getString("video_url"));
			}
		} 
		movie.setGenre(jsonObjectBaseInfo.getString("genre"));
		movie.setIntro(jsonObjectBaseInfo.getString("desc"));
		movie.setScore(Float.valueOf(jsonObjectBaseInfo.getString("score")));
		movie.setScore_count(jsonObjectBaseInfo.getInt("score_count")); 
		movie.setId(movie_id);
		MovieDao movieDao = new MovieDao();
		movieDao.updateMovie(movie);
	}

	private void getDirectorNameAndSave(JSONObject jsonObjectBaseInfo,
			int movie_id) throws SQLException { 
		String director = "";
		if(jsonObjectBaseInfo.getString("directors") == null){
			director = jsonObjectBaseInfo.getString("directors");
		}
		
		//插入数据库 ， 存放导演名 和 电影名
		DirectorMovieDao directorDao = new DirectorMovieDao();
		directorDao.addDirector(new DirectorMovie(director , movie_id));
	}

	private void getActorMovieNameAndSave(JSONObject jsonObjectBaseInfo , int movie_id) throws SQLException { 
		String actors = jsonObjectBaseInfo.getString("actors");
		//分离演员名 并且插入数据库
		String actorsArr[] = actors.split("/");
		List<ActorMovie> actorMovieList = new ArrayList<ActorMovie>();
		for(String actor: actorsArr){
			actorMovieList.add(new ActorMovie(actor.trim() , movie_id));
		} 
		ActorMovieDao actorMovieDao = new ActorMovieDao();
		actorMovieDao.addActor(actorMovieList);
	}
 
}
