package com.weibomovie.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.weibomovie.db.DBUtil;
import com.weibomovie.model.Actor;
import com.weibomovie.model.Director;

public class DirectorDao {
	
	public void addDirector(Director director) throws SQLException{
		
		Connection conn  = DBUtil.getConnection();
		int movie_id = director.getMovie_id();
		String sql = "select *from director where movie_id=? ";
		PreparedStatement ptmtSelect = conn.prepareStatement(sql);
		ptmtSelect.setInt(1, movie_id);
		ResultSet rs = ptmtSelect.executeQuery();
		rs.last();
		int rawCount = rs.getRow();
		if(rawCount == 0){
			sql = "insert into director (name , movie_id) values(?,?)";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, director.getName());
			ptmt.setInt(2, director.getMovie_id());
			ptmt.execute();
		}
	}

	public String getDirectorByMovie(int movie_id) throws SQLException {
		String director = "" ;
		Connection conn = DBUtil.getConnection();
		String sql = "select *from director where movie_id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, movie_id);
		ResultSet rs = ptmt.executeQuery();
		
		while(rs.next()){
			director += rs.getString("name"); 
		}
		return director;
	}
	
}
