package com.weibomovie.model;

public class Actor {
	
	private int id;
	private String name;
	private String url; 
	private String job = "演员";
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Actor() {
		super();
		this.job = "演员";
	}
	public Actor(int id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.job = "演员";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", url=" + url + "]";
	} 
	
}
