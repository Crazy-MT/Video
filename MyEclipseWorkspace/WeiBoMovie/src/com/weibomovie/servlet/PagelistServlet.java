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
import com.weibomovie.dao.PageListDao;
import com.weibomovie.db.Constant;
import com.weibomovie.model.Movie;
import com.weibomovie.model.Pagelist;
import com.weibomovie.weiboapi.PageListData;

public class PagelistServlet extends HttpServlet {

	public PagelistServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		if (Constant.isOpenDownloadData()) {
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

		// 将pagelist展示出来
		PrintWriter out = response.getWriter();
		List<Pagelist> pageList = new ArrayList<Pagelist>();
		PageListDao pageListDao = new PageListDao();
		JSONObject jsonObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		try {
			pageList = pageListDao.query();
			dataObject.put("pagelist", pageList);
			jsonObject.put("ret", "success");
			jsonObject.put("data", dataObject);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("ret", "error");
			jsonObject.put("data", "");
		}
		System.out.println(jsonObject);
		out.println(jsonObject);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
	}

}
