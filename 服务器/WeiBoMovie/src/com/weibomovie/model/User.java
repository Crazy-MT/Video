package com.weibomovie.model;

public class User {
	
	private Integer id ;
	private String userName ;
	private Integer likeId;
	private Integer doneId;
	private String icon;
	private long weibo_id;
	
	
	public User(Integer id, String userName, Integer likeId, Integer doneId,
			String icon, long weibo_id) {
		super();
		this.id = id;
		this.userName = userName;
		this.likeId = likeId;
		this.doneId = doneId;
		this.icon = icon;
		this.weibo_id = weibo_id;
	}
	public User() { 
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", likeId="
				+ likeId + ", doneId=" + doneId + ", icon=" + icon
				+ ", weibo_id=" + weibo_id + "]";
	}
	public long getWeibo_id() {
		return weibo_id;
	}
	public void setWeibo_id(long weibo_id) {
		this.weibo_id = weibo_id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public Integer getDoneId() {
		return doneId;
	}
	public void setDoneId(Integer doneId) {
		this.doneId = doneId;
	}
	
	
	
}
