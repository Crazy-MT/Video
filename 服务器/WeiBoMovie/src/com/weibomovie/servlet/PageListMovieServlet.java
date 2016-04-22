package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.weibomovie.dao.MovieDao;
import com.weibomovie.model.Movie;

public class PageListMovieServlet extends HttpServlet {

	public PageListMovieServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pageListId = request.getParameter("pagelistid");
 
		MovieDao movieDao = new MovieDao();
		List<Movie> movies = new ArrayList<Movie>();
		JSONObject dataJson = new JSONObject();
		JSONObject resultJson = new JSONObject();
		try {
			 movies = movieDao.getPageListMovie(pageListId);
			 dataJson.put("movie", movies);
			 resultJson.put("ret", "success");
			 resultJson.put("data", dataJson);
		} catch (SQLException e) {
			resultJson.put("ret", "error");
			resultJson.put("data", "");
			e.printStackTrace();
		} 
		System.out.println(resultJson);
		out.println(resultJson.toString());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
