package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibomovie.dao.CommentDao;
import com.weibomovie.model.Comment;

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
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//将评论信息存入数据库、并且更新电影评分和评论数量
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("userid") != null ){
			Comment comment = new Comment();
			comment.setUserid(Long.valueOf(request.getParameter("userid")));
			comment.setText(request.getParameter("comment"));
			comment.setMovieid(Integer.valueOf(request.getParameter("movieid")));
			CommentDao commentDao = new CommentDao();
			try {
				commentDao.addComment(comment);
				out.println(true);
				
			} catch (Exception e) { 
				e.printStackTrace();
				out.println(false);
			}
			
			out.flush();
			out.close();
		} else if (request.getParameter("movie_id") != null){
			/*Comment comment = new Comment();
			comment.setUserid(Integer.valueOf(request.getParameter("movie_id")));
			comment.setText(request.getParameter("comment"));
			comment.setMovieid(Integer.valueOf(request.getParameter("movieid")));*/
			CommentDao commentDao = new CommentDao();
			try {
				commentDao.getCommentFromMovieId(Integer.valueOf(request.getParameter("movie_id")));
				out.println(true);
				
			} catch (Exception e) { 
				e.printStackTrace();
				out.println(false);
			}
			
			out.flush();
			out.close();
		}
		
		
	}

	public void init() throws ServletException {
	}
}
