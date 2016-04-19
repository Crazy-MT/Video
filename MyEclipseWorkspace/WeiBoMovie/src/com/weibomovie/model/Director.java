package com.weibomovie.model;

public class Director {
	
	private int id;
	private String name;
	private String url; 
	private String job = "导演";
	
	
	
	public String getJob() {
		return job;
	}

	public Director() {
		super();
		this.job = "导演";
	}
	public Director(int id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.job = "导演";
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
		return "Director [id=" + id + ", name=" + name + ", url=" + url + "]";
	} 
	
}
