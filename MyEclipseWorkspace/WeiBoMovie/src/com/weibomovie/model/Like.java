package com.weibomovie.model;

public class Like {
	
	private int id;
	private Long weibo_id;
	private int movie_id;
	
	
	public Like() {
		super();
	}
	public Like(int id, Long weibo_id, int movie_id) {
		super();
		this.id = id;
		this.weibo_id = weibo_id;
		this.movie_id = movie_id;
	}
	@Override
	public String toString() {
		return "Like [id=" + id + ", weibo_id=" + weibo_id + ", movie_id="
				+ movie_id + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getWeibo_id() {
		return weibo_id;
	}
	public void setWeibo_id(Long weibo_id) {
		this.weibo_id = weibo_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	
	

}
