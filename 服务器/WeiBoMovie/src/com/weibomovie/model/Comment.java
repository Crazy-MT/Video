package com.weibomovie.model;

public class Comment {

	private int id ;
	private long userid;
	private int movieid;
	private String text;
	private float score;
	private long createdtime;
	
	
	public Comment() {
		super();
	}
	public Comment(int id, long userid, int movieid, String text, float score , long createdtime) {
		super();
		this.id = id;
		this.userid = userid;
		this.movieid = movieid;
		this.text = text;
		this.score = score;
		this.createdtime = createdtime;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userid=" + userid + ", movieid="
				+ movieid + ", text=" + text + ", score=" + score +  "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public long getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(long createdtime) {
		this.createdtime = createdtime;
	}
	
	
	
	
}
