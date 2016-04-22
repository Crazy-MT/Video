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
import com.weibomovie.model.Goddess;
import com.weibomovie.model.User;

public class LogDao {

	public void addLog(User user) throws Exception {
		Connection conn = DBUtil.getConnection();

		String sql = "" + "insert into log"
				+ "(user_name, weibo_id , is_login  )"
				+ "values(" + "?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user.getUserName());
		ptmt.setLong(2, user.getWeibo_id());
		ptmt.setInt(3, 1); 
		ptmt.execute();
	}

	public void logout(long weibo_id) throws SQLException { 
		Connection conn = DBUtil.getConnection();
		String sql = "update log set is_login=0 , logout_date=current_time()  where is_login=1 and weibo_id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql); 
		ptmt.setLong(1, weibo_id);
		ptmt.execute();	
	}
}
