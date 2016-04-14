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
import com.weibomovie.model.Movie;
import com.weibomovie.weiboapi.ComingData; 

public class ComingServlet extends HttpServlet {
 
	public ComingServlet() {
		super();
	}
 
	public void destroy() {
		super.destroy();  
	}
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ComingData comingData = new ComingData();
		try {
			comingData.httpPost();
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Movie> movieList = new ArrayList<Movie>();
		MovieDao movieDao = new MovieDao();
		JSONObject jsonObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		try {
			movieList = movieDao.queryIsComing();
			dataObject.put("coming",movieList);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
