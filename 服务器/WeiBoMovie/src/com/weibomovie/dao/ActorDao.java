package com.weibomovie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weibomovie.db.DBUtil;
import com.weibomovie.model.Actor;
import com.weibomovie.model.ActorMovie;
import com.weibomovie.model.Director;

public class ActorDao {

	public void addActor(List<Actor> actors) throws SQLException {

		Connection conn = DBUtil.getConnection();

		for (int i = 0; i < actors.size(); ++i) {
			Actor actor = actors.get(i);
			String sql = "insert into actor (name , url) values(?, ?) ";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, actor.getName());
			ptmt.setString(2, actor.getUrl());
			ptmt.execute();
		}
	}

	public Actor getActor(String actorStr) throws SQLException {
		Connection conn = DBUtil.getConnection();
		Actor actor = null;

		String sql = "select *from actor where name=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, actorStr);
		ResultSet rs = ptmt.executeQuery();
		while (rs.next()) {
			actor = new Actor(rs.getInt("id"), rs.getString("name"),
					rs.getString("url"));
			System.out.println(actor.toString());
		}
		return actor;
	}

	@SuppressWarnings("null")
	public List<Actor> getActorByMovieId(String[] actorStrArr)
			throws SQLException {

		List<Actor> actorList = new ArrayList<Actor>();

		Actor actor = null;
		for (int i = 0; i < actorStrArr.length; ++i) {
			actor = getActor(actorStrArr[i].trim());
			System.out.println(actorStrArr[i]);
			if (actor != null) {
				actorList.add(actor);
			}
		}
		
		for ( int i = 0 ; i < actorList.size() - 1 ; i ++ ) {  
		     for ( int j = actorList.size() - 1 ; j > i; j -- ) {  
		       if (actorList.get(j).getName().equals(actorList.get(i).getName())) {  
		    	   actorList.remove(j);  
		       }   
		      }   
		    }   

		return actorList;
	}
}
