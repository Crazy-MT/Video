package com.weibomovie.model;

public class Pagelist {
	private int id;
	private String name;
	private String description;
	private String cover_url;
	private int movie_count;
	
	
	
	public Pagelist() {
		super();
	}
	
	public Pagelist(String name, String description, String cover_url,
			int movie_count) {
		super();
		this.name = name;
		this.description = description;
		this.cover_url = cover_url;
		this.movie_count = movie_count;
	}

	public Pagelist(int id, String name, String description, String cover_url,
			int movie_count) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cover_url = cover_url;
		this.movie_count = movie_count;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover_url() {
		return cover_url;
	}
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}
	public int getMovie_count() {
		return movie_count;
	}
	public void setMovie_count(int movie_count) {
		this.movie_count = movie_count;
	}
	@Override
	public String toString() {
		return "Pagelist [id=" + id + ", name=" + name + ", description="
				+ description + ", cover_url=" + cover_url + ", movie_count="
				+ movie_count + "]";
	}
	
	
}
