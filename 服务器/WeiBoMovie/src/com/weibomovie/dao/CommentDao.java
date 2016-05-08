package com.weibomovie.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.weibomovie.db.DBUtil;
import com.weibomovie.model.Comment;
import com.weibomovie.model.User;

public class CommentDao {

	public void addComment(Comment comment) throws Exception {
		Connection conn = DBUtil.getConnection();

		String sql = "" + "insert into comment"
				+ "(userid, movieid , text,createdtime  )" + "values("
				+ "?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setLong(1, comment.getUserid());
		ptmt.setLong(2, comment.getMovieid());
		ptmt.setString(3, comment.getText());
		ptmt.setDate(4, new Date(System.currentTimeMillis()));
		ptmt.execute();
	}

	public void getCommentFromMovieId(int movieId) throws SQLException { 
		Connection conn = DBUtil.getConnection();

		String sql = "SELECT b.user_name , b.icon , b.weibo_id , a.text FROM comment AS a,user AS b WHERE a.movieid=? AND a.userid=b.weibo_id";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, movieId);
		ptmt.execute();
		
	}
 
}
