package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibomovie.db.Constant;
import com.weibomovie.weiboapi.ComingData;
import com.weibomovie.weiboapi.MovieData;
import com.weibomovie.weiboapi.PageListData;

public class LoadMovieDataServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadMovieDataServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		ComingData comingData = new ComingData();
		try {
			comingData.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		MovieData movieData = new MovieData();
		try {
			movieData.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		MovieData movieData1 = new MovieData();
		try {
			movieData1.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PageListData pageListData = new PageListData();

		try {
			// 下载pagelist数据 、 并存放到数据库
			pageListData.httpPost();
			// 下载pagelist对应的电影,，更新pagelist并存放电影至数据库
			pageListData.getPageListMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("毛通");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		ComingData comingData = new ComingData();
		try {
			comingData.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		MovieData movieData = new MovieData();
		try {
			movieData.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		MovieData movieData1 = new MovieData();
		try {
			movieData1.httpPost();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		PageListData pageListData = new PageListData();

		try {
			// 下载pagelist数据 、 并存放到数据库
			pageListData.httpPost();
			// 下载pagelist对应的电影,，更新pagelist并存放电影至数据库
			pageListData.getPageListMovie();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
