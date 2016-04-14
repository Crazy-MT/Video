package com.weibomovie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.weibomovie.dao.LogDao;
import com.weibomovie.dao.UserDao;
import com.weibomovie.model.User;

public class UserServlet extends HttpServlet {

	public UserServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		User user = new User();
		user.setWeibo_id(Long.valueOf(request.getParameter("weibo_id")));
		user.setUserName(request.getParameter("user_name"));
		user.setIcon(request.getParameter("icon")); 
		UserDao userDao = new UserDao();
		PrintWriter out = response.getWriter();
		JSONObject dataJson = new JSONObject();
		JSONObject resultJson = new JSONObject();

		LogDao logDao = new LogDao();
		try {
			logDao.addLog(user);
			userDao.addUser(user);
			resultJson.put("result", "success");
			resultJson.put("data", dataJson);
		} catch (Exception e) {
			resultJson.put("result", "error");
			e.printStackTrace();
		} 
		out.println(resultJson.toString());
		out.flush();
		out.close(); 
	}

	public void init() throws ServletException {
	}

}
