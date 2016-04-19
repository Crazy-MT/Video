package com.weibomovie.model;

public class ActorMovie {
	private int id ;
	private int movie_id;
	private String name;
	
	
	
	public ActorMovie() {
		super();
	}
	public ActorMovie(int id, int movie_id, String name) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.name = name;
	}
	public ActorMovie(String actor, int movie_id) {
		this.name = actor;
		this.movie_id = movie_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ActorMovie [id=" + id + ", movie_id=" + movie_id + ", name="
				+ name + "]";
	}
	
	
}