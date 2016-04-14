package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentServlet extends HttpServlet {
 
	public CommentServlet() {
		super();
	}
	 
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		//从接口得到长影评，并且写入数据库
		
		//将所有影评信息返回
		
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		//将评论信息存入数据库、并且更新电影评分和评论数量
		
		PrintWriter out = response.getWriter();
		
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}
}
