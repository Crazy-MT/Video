package com.weibomovie.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weibomovie.db.DBUtil;
import com.weibomovie.model.Goddess;
import com.weibomovie.model.Movie;
import com.weibomovie.model.User;

public class MovieDao {

	/**
	 * 获取所有热映电影
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Movie> queryIsShowing() throws Exception {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from movie  where is_showing=1");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet rs = ptmt.executeQuery();
		Movie movie = null;
		while (rs.next()) {
			movie = new Movie();
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("movie_name"));
			movie.setGenre(rs.getString("genre"));
			movie.setIntro(rs.getString("intro"));
			movie.setLarge_poster_url(rs.getString("large_poster_url"));
			movie.setPoster_url(rs.getString("poster_url"));
			movie.setScore(rs.getFloat("score"));
			movie.setScore_count(rs.getInt("score_count"));
			movie.setPage_list_id(rs.getInt("pagelistid"));

			result.add(movie);
		}
		return result;
	}

	/**
	 * 获取所有即将上映电影
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Movie> queryIsComing() throws Exception {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from movie  where is_coming=1");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet rs = ptmt.executeQuery();
		Movie movie = null;
		while (rs.next()) {
			movie = new Movie();
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("movie_name"));
			movie.setGenre(rs.getString("genre"));
			movie.setIntro(rs.getString("intro"));
			movie.setLarge_poster_url(rs.getString("large_poster_url"));
			movie.setPoster_url(rs.getString("poster_url"));
			movie.setScore(rs.getFloat("score"));
			movie.setScore_count(rs.getInt("score_count"));
			movie.setPage_list_id(rs.getInt("pagelistid"));
			movie.setRelease_date(rs.getString("release_date"));
			movie.setIs_coming(rs.getInt("is_coming"));
			result.add(movie);
		}
		return result;
	}

	/**
	 * 根据用户信息，获取所有即将上映电影
	 * 
	 * @param weibo_id
	 * @return
	 * @throws Exception
	 */
	public List<Movie> queryIsLike(Long weibo_id) throws Exception {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select movie_id from likemovie  where weibo_id=? ");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setLong(1, weibo_id);
		ResultSet rs = ptmt.executeQuery();

		List<Integer> movieIdList = new ArrayList<Integer>();
		while (rs.next()) {
			movieIdList.add(rs.getInt("movie_id"));
		}

		String sql = "select *from movie where is_showing=1 ";
		PreparedStatement ptmtMovie = conn.prepareStatement(sql);
		ResultSet rsMovie = ptmtMovie.executeQuery();
		Movie movie = null;
		while (rsMovie.next()) {
			movie = new Movie();
			movie.setId(rsMovie.getInt("id"));
			movie.setName(rsMovie.getString("movie_name"));
			movie.setGenre(rsMovie.getString("genre"));
			movie.setIntro(rsMovie.getString("intro"));
			movie.setLarge_poster_url(rsMovie.getString("large_poster_url"));
			movie.setPoster_url(rsMovie.getString("poster_url"));
			movie.setScore(rsMovie.getFloat("score"));
			movie.setScore_count(rsMovie.getInt("score_count"));
			movie.setPage_list_id(rsMovie.getInt("pagelistid"));
			movie.setRelease_date(rsMovie.getString("release_date"));
			movie.setIs_Like(0);
			result.add(movie);
		}

		for (int movie_id : movieIdList) {
			for (int i = 0; i < result.size(); i++) {
				if (movie_id == result.get(i).getId()) {
					result.get(i).setIs_Like(1);
					break;
				}
			}
		}

		return result;
	}

	public void saveDatas(List<Movie> movies) throws SQLException {
		Connection conn = DBUtil.getConnection();
		for (int j = 0; j < movies.size(); j++) {
			Movie movie = movies.get(j);
			String sqlFirst = "select * from movie where movie_name=?";
			PreparedStatement ptmtFirst = conn.prepareStatement(sqlFirst);
			ptmtFirst.setString(1, movie.getName());
			ResultSet rs = ptmtFirst.executeQuery();
			rs.last();
			int rawCount = rs.getRow();
			if (rawCount == 0) {
				String sql = ""
						+ "insert into movie"
						+ "(id,movie_name,genre,intro,poster_url,large_poster_url,score,score_count,is_showing)"
						+ "values(" + "?,?,?,?,?,?,?,?,?) ";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, movie.getId());
				ptmt.setString(2, movie.getName());
				ptmt.setString(3, movie.getGenre());
				ptmt.setString(4, movie.getIntro());
				ptmt.setString(5, movie.getPoster_url());
				ptmt.setString(6, movie.getLarge_poster_url());
				ptmt.setFloat(7, movie.getScore());
				ptmt.setInt(8, movie.getScore_count());
				ptmt.setInt(9, movie.getIs_showing());
				ptmt.execute();
			}
		}
	}

	public void saveMovieFromPagelist(List<Movie> movies) throws SQLException {
		Connection conn = DBUtil.getConnection();
		for (int j = 0; j < movies.size(); j++) {
			Movie movie = movies.get(j);
			String sqlFirst = "select * from movie where movie_name=?";
			PreparedStatement ptmtFirst = conn.prepareStatement(sqlFirst);
			ptmtFirst.setString(1, movie.getName());
			ResultSet rs = ptmtFirst.executeQuery();
			rs.last();
			int rawCount = rs.getRow();
			if (rawCount == 0) {
				String sql = "" + "insert into movie"
						+ "(id,movie_name,poster_url,score,pagelistid)"
						+ "values(" + "?,?,?,?,?) ";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, movie.getId());
				ptmt.setString(2, movie.getName());
				ptmt.setString(3, movie.getPoster_url());
				ptmt.setFloat(4, movie.getScore());
				ptmt.setInt(5, movie.getPage_list_id());
				ptmt.execute();
			}
		}
	}

	public List<Movie> getPageListMovie(String pageListId) throws SQLException {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from movie where pagelistid=? ");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setInt(1, Integer.valueOf(pageListId));
		ResultSet rs = ptmt.executeQuery();
		Movie movie = null;
		while (rs.next()) {
			movie = new Movie();
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("movie_name"));
			movie.setGenre(rs.getString("genre"));
			movie.setIntro(rs.getString("intro"));
			movie.setLarge_poster_url(rs.getString("large_poster_url"));
			movie.setPoster_url(rs.getString("poster_url"));
			movie.setScore(rs.getFloat("score"));
			movie.setScore_count(rs.getInt("score_count"));
			movie.setPage_list_id(rs.getInt("pagelistid"));
			result.add(movie);
		}
		return result;
	}

	public void saveDatasFromComing(List<Movie> movies) throws SQLException {
		Connection conn = DBUtil.getConnection();
		for (int j = 0; j < movies.size(); j++) {
			Movie movie = movies.get(j);
			String sqlFirst = "select * from movie where movie_name=?";
			PreparedStatement ptmtFirst = conn.prepareStatement(sqlFirst);
			ptmtFirst.setString(1, movie.getName());
			ResultSet rs = ptmtFirst.executeQuery();
			rs.last();
			int rawCount = rs.getRow();
			if (rawCount == 0) {
				String sql = ""
						+ "insert into movie"
						+ "(id,movie_name,genre,intro,poster_url,large_poster_url,score,score_count,is_showing,is_coming,release_date)"
						+ "values(" + "?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, movie.getId());
				ptmt.setString(2, movie.getName());
				ptmt.setString(3, movie.getGenre());
				ptmt.setString(4, movie.getIntro());
				ptmt.setString(5, movie.getPoster_url());
				ptmt.setString(6, movie.getLarge_poster_url());
				ptmt.setFloat(7, movie.getScore());
				ptmt.setInt(8, movie.getScore_count());
				ptmt.setInt(9, movie.getIs_showing());
				ptmt.setInt(10, movie.getIs_coming());
				ptmt.setString(11, movie.getRelease_date());
				ptmt.execute();
			}
		}

	}

	public List<Movie> queryAllLike(Long weibo_id) throws SQLException {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select movie_id from likemovie  where weibo_id=? ");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setLong(1, weibo_id);
		ResultSet rs = ptmt.executeQuery();

		List<Integer> movieIdList = new ArrayList<Integer>();
		while (rs.next()) {
			movieIdList.add(rs.getInt("movie_id"));
		}

		for (int movieId : movieIdList) {
			String sql = "select *from movie where id = ? ";
			PreparedStatement ptmtMovie = conn.prepareStatement(sql);
			ptmtMovie.setInt(1, movieId);
			ResultSet rsMovie = ptmtMovie.executeQuery();
			Movie movie = null;
			while (rsMovie.next()) {
				movie = new Movie();
				movie.setId(rsMovie.getInt("id"));
				movie.setName(rsMovie.getString("movie_name"));
				movie.setGenre(rsMovie.getString("genre"));
				movie.setIntro(rsMovie.getString("intro"));
				movie.setLarge_poster_url(rsMovie.getString("large_poster_url"));
				movie.setPoster_url(rsMovie.getString("poster_url"));
				movie.setScore(rsMovie.getFloat("score"));
				movie.setScore_count(rsMovie.getInt("score_count"));
				movie.setPage_list_id(rsMovie.getInt("pagelistid"));
				movie.setRelease_date(rsMovie.getString("release_date"));
				movie.setIs_Like(0);
				result.add(movie);
			}
		}
		return result;
	}

	public List<Movie> queryIsLikeComing(Long weibo_id) throws SQLException {
		List<Movie> result = new ArrayList<Movie>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select movie_id from likemovie  where weibo_id=? ");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setLong(1, weibo_id);
		ResultSet rs = ptmt.executeQuery();

		List<Integer> movieIdList = new ArrayList<Integer>();
		while (rs.next()) {
			movieIdList.add(rs.getInt("movie_id"));
		}

		String sql = "select *from movie where is_coming=1 ";
		PreparedStatement ptmtMovie = conn.prepareStatement(sql);
		ResultSet rsMovie = ptmtMovie.executeQuery();
		Movie movie = null;
		while (rsMovie.next()) {
			movie = new Movie();
			movie.setId(rsMovie.getInt("id"));
			movie.setName(rsMovie.getString("movie_name"));
			movie.setGenre(rsMovie.getString("genre"));
			movie.setIntro(rsMovie.getString("intro"));
			movie.setLarge_poster_url(rsMovie.getString("large_poster_url"));
			movie.setPoster_url(rsMovie.getString("poster_url"));
			movie.setScore(rsMovie.getFloat("score"));
			movie.setScore_count(rsMovie.getInt("score_count"));
			movie.setPage_list_id(rsMovie.getInt("pagelistid"));
			movie.setRelease_date(rsMovie.getString("release_date"));
			movie.setIs_Like(0);
			result.add(movie);
		}

		for (int movie_id : movieIdList) {
			for (int i = 0; i < result.size(); i++) {
				if (movie_id == result.get(i).getId()) {
					result.get(i).setIs_Like(1);
					break;
				}
			}
		}

		return result;
	}

	public void updateMovie(Movie movie) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + " update movie " + " set video_url=? " + " "
				+ " where id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, movie.getVideo_url());
		ptmt.setInt(2, movie.getId());
		ptmt.execute();
	}

	public Movie getMovieDetail(int movie_id) throws SQLException {
		Movie movie = new Movie();
		Connection conn = DBUtil.getConnection();
		
		String sql = "select *from movie where id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, movie_id);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("movie_name"));
			movie.setGenre(rs.getString("genre"));
			movie.setIntro(rs.getString("intro"));
			movie.setLarge_poster_url(rs.getString("large_poster_url"));
			movie.setPoster_url(rs.getString("poster_url"));
			movie.setScore(rs.getFloat("score"));
			movie.setScore_count(rs.getInt("score_count"));
			movie.setPage_list_id(rs.getInt("pagelistid"));
			movie.setRelease_date(rs.getString("release_date"));
			movie.setVideo_url(rs.getString("video_url"));			
		} 
		return movie; 
	}
}
