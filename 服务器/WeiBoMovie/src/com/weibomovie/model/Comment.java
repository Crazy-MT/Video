package com.weibomovie.model;

public class Comment {

	private int id ;
	private long userid;
	private int movieid;
	private String movieName;
	private String text;
	private float score;
	private long createdtime;
	private String userName;
	private String userIcon; 
	
	
	
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Comment(long userid, int movieid, String text, String userName,
			String userIcon) {
		super();
		this.userid = userid;
		this.movieid = movieid;
		this.text = text;
		this.userName = userName;
		this.userIcon = userIcon;
	}
	
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
				+ movieid + ", text=" + text + ", score=" + score
				+ ", createdtime=" + createdtime + ", userName=" + userName
				+ ", userIcon=" + userIcon + "]";
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
