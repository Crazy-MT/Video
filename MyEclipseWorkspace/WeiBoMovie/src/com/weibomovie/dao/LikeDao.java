package com.weibomovie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weibomovie.db.DBUtil;
import com.weibomovie.model.Like;
import com.weibomovie.model.Movie;

public class LikeDao {

	public void addLike(Like like) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + "insert into likemovie" + " (movie_id,weibo_id)"
				+ "values(?,?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, like.getMovie_id());
		ptmt.setLong(2, like.getWeibo_id());
		ptmt.execute();
	}

}
