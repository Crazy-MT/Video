package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
 

import com.weibomovie.dao.MovieDao;
import com.weibomovie.db.Constant;
import com.weibomovie.juheapi.MovieJuheData;
import com.weibomovie.model.Movie;
import com.weibomovie.weiboapi.MovieData;

public class MovieServlet extends HttpServlet {

	public MovieServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Movie> movieList = new ArrayList<Movie>();
		MovieDao movieDao = new MovieDao();
		JSONObject jsonObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		try {
			//查询数据库，获得电影信息
			movieList = movieDao.queryIsShowing();
			dataObject.put("movie",movieList);
			jsonObject.put("ret", "success");
			jsonObject.put("data", dataObject);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("ret", "error");
			jsonObject.put("data", "");
		}  
		out.println(jsonObject);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		Long weibo_id = Long.valueOf(request.getParameter("weibo_id"));
		PrintWriter out = response.getWriter();
		List<Movie> movieList = new ArrayList<Movie>();
		MovieDao movieDao = new MovieDao();
		JSONObject jsonObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		try {
			movieList = movieDao.queryIsLike(weibo_id);
			dataObject.put("movie",movieList);
			jsonObject.put("ret", "success");
			jsonObject.put("data", dataObject);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("ret", "error");
			jsonObject.put("data", "");
		} 
		out.println(jsonObject);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
