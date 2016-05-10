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
import com.weibomovie.model.Director;
import com.weibomovie.model.Movie;
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

	public List<Comment> getCommentFromMovieId(int movieId) throws SQLException { 
		Connection conn = DBUtil.getConnection();
		List<Comment> commentList = new ArrayList<Comment>();
		Comment comment  = null;
		System.out.println(movieId);
		String sql = "SELECT b.user_name , b.icon , b.weibo_id , a.text FROM comment AS a,user AS b WHERE a.movieid=? AND a.userid=b.weibo_id";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, movieId);
		ResultSet rs = ptmt.executeQuery();
		//System.out.println(rs.toString());
		while (rs.next()) {
			//System.out.println(rs.toString());
			comment = new Comment(rs.getLong("weibo_id") , movieId , rs.getString("text") , rs.getString("user_name"), rs.getString("icon"));
			commentList.add(comment);
		}
		
		return commentList;
	}

	public List<Movie> getCommentFromUserId(Long userId) throws SQLException {
		Connection conn = DBUtil.getConnection();
		List<Movie> movieList = new ArrayList<Movie>();
		Movie movie  = null;
		System.out.println(userId);
		String sql = "SELECT b.movie_name , b.poster_url , b.id  FROM comment AS a,movie AS b WHERE a.userid=? AND a.movieid=b.id";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setLong(1, userId);
		ResultSet rs = ptmt.executeQuery();
		//System.out.println(rs.toString());
		while (rs.next()) {
			//System.out.println(rs.toString());
			movie = new Movie();
			movie.setName(rs.getString("movie_name"));
			movie.setPoster_url(rs.getString("poster_url"));
			movie.setId(rs.getInt("id"));
			movieList.add(movie);
		} 
		return movieList;
	}
 
}
