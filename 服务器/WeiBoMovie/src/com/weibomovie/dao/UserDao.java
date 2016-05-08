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
import com.weibomovie.model.User;

public class UserDao {
	
	
	public void addUser(User user) throws Exception{
		Connection conn=DBUtil.getConnection();
		
		String sqlSelect = "select * from user where weibo_id =? ";
		PreparedStatement ptmtFirst = conn.prepareStatement(sqlSelect);
		ptmtFirst.setLong(1, user.getWeibo_id());
		ResultSet rs = ptmtFirst.executeQuery();
		rs.last();
		int rawCount = rs.getRow();
		if (rawCount == 0) {
			String sql="" +
					"insert into user" +
					"(user_name, icon, weibo_id)" +
					"values(" +
					"?,?,?)";
			PreparedStatement ptmt=conn.prepareStatement(sql); 
			ptmt.setString(1, user.getUserName());
			ptmt.setString(2, user.getIcon());
			ptmt.setLong(3, user.getWeibo_id());
			ptmt.execute();
		}
	}
	
	public String getUserName() throws Exception{
		String name = null ;

		Connection conn=DBUtil.getConnection();
		ResultSet rs=conn.prepareStatement("select * from user ").executeQuery();
		while(rs.next()){
			name = rs.getString("user_name");
		}
		return name;
	}
	
	public User get(Integer id) throws SQLException{
		User u=null;
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" select * from user ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			u=new User();
			u.setId(rs.getInt("id"));
			u.setUserName(rs.getString("user_name"));
		}
		return u;
	}
	
	public void updateUser(User u) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" update user " +
				" set user_name=?,like_movie_id=?,done_movie_id=?" +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1, u.getUserName());
		ptmt.setInt(2, u.getLikeId());
		ptmt.setInt(3, u.getDoneId()); 
		ptmt.setInt(4, u.getId());
		ptmt.execute();
	}
	
	public void delUser(Integer id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" delete from user " +
				" where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	/*
	public List<User> query() throws Exception{
		List<User> result=new ArrayList<User>();
		
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select id,user_name, from imooc_goddess  ");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		ResultSet rs=ptmt.executeQuery();
		
		User u=null;
		while(rs.next()){
			u=new User();
			u.setId(rs.getInt("id"));
			u.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			result.add(u);
		}
		return result;
	}
	public List<User> query(String name,String mobile,String email) throws Exception{
		List<User> result=new ArrayList<User>();
		
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from user  ");
		
		sb.append(" where User like ? and mobile like ? and email like ?");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		ptmt.setString(1, "%"+name+"%");
		ptmt.setString(2, "%"+mobile+"%");
		ptmt.setString(3, "%"+email+"%");
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		
		Goddess g=null;
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
			
			result.add(g);
		}
		return result;
	}
	public List<Goddess> query(List<Map<String, Object>> params) throws Exception{
		List<Goddess> result=new ArrayList<Goddess>();
		
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from imooc_goddess where 1=1 ");
		
		if(params!=null&&params.size()>0){
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sb.append(" and  "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
			}
		}
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		System.out.println(sb.toString());
		ResultSet rs=ptmt.executeQuery();
		
		Goddess g=null;
		while(rs.next()){
			g=new Goddess();
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isdel"));
			
			result.add(g);
		}
		return result;
	}*/
	
}
