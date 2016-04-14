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
import com.weibomovie.model.Pagelist;
import com.weibomovie.model.User;

public class PageListDao {
	
	//得到所有pagelist
	public List<Pagelist> query() throws Exception{
		List<Pagelist> result=new ArrayList<Pagelist>();
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from pagelist  ");		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());		
		ResultSet rs=ptmt.executeQuery();		
		Pagelist pageList=null;
		while(rs.next()){
			pageList=new Pagelist();
			pageList.setId(rs.getInt("id"));
			pageList.setName(rs.getString("name"));
			pageList.setDescription(rs.getString("description"));
			pageList.setCover_url(rs.getString("cover_url"));
			pageList.setMovie_count(rs.getInt("movie_count"));
			result.add(pageList); 
		}
		return result;
	}

	public void saveDatas(List<Pagelist> pageLists) throws SQLException {
		Connection conn=DBUtil.getConnection();		
		for(int j = 0 ; j < pageLists.size(); j++){
			Pagelist pageList = pageLists.get(j);
			String sqlFirst = "select * from pagelist where name=?";
			PreparedStatement ptmtFirst = conn.prepareStatement(sqlFirst);
			ptmtFirst.setString(1, pageList.getName());
			ResultSet rs=ptmtFirst.executeQuery();
			rs.last();
			int rawCount = rs.getRow();
			if(rawCount == 0){
				System.out.println("");
				String sql="" +
						"insert into pagelist" +
						"(id,name,description,cover_url,movie_count)" +
						"values(" +
						"?,?,?,?,?) ";
				PreparedStatement ptmt=conn.prepareStatement(sql);
				ptmt.setInt(1, pageList.getId());
				ptmt.setString(2, pageList.getName());
				ptmt.setString(3, pageList.getDescription());
				ptmt.setString(4, pageList.getCover_url());
				ptmt.setInt(5, pageList.getMovie_count());
				ptmt.execute();
			}
		} 
	}

	public List<Integer> getPageListId() throws Exception{ 
		List<Integer> idList = new ArrayList<Integer>();
		Connection conn=DBUtil.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select id from pagelist  "); 
		PreparedStatement ptmt=conn.prepareStatement(sb.toString()); 
		ResultSet rs=ptmt.executeQuery();  
		while(rs.next()){
			idList.add(rs.getInt("id"));
		} 
		return idList;
	}

	public void update(Pagelist pageList1) throws SQLException { 
		StringBuilder sb=new StringBuilder();
		sb.append("update pagelist " +
				"set description=?,cover_url=? " +
				"where id=?  "); 
		Connection conn=DBUtil.getConnection();
		PreparedStatement ptmt=conn.prepareStatement(sb.toString()); 
		ptmt.setString(1, pageList1.getDescription());
		ptmt.setString(2, pageList1.getCover_url());
		ptmt.setInt(3,pageList1.getId()); 
		ptmt.execute();
	}
}
