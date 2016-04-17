package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.weibomovie.dao.LikeDao;
import com.weibomovie.model.Like;

public class LikeMovieServlet extends HttpServlet {

	public LikeMovieServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		boolean isLike = request.getParameter("is_like").equals("true") ? true
				: false;

		Like like = new Like();
		like.setMovie_id(Integer.valueOf(request.getParameter("movie_id")));
		like.setWeibo_id(Long.valueOf(request.getParameter("weibo_id")));
		LikeDao likeDao = new LikeDao();

		PrintWriter out = response.getWriter();
		JSONObject dataJson = new JSONObject();
		JSONObject resultJson = new JSONObject();
		try {
			if (isLike) {
				likeDao.addLike(like);
			} else {
				likeDao.deleteLike(like);
			}

			resultJson.put("result", "success");
			resultJson.put("data", dataJson);
		} catch (Exception e) {
			resultJson.put("result", "error");
			e.printStackTrace();
		}
		System.out.println(resultJson.toString());
		out.println(resultJson.toString());
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
