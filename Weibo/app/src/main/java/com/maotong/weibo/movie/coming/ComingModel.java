package com.maotong.weibo.movie.coming;

public class ComingModel {
	
	private int id;
	private String name ;
	private String genre;
	private String intro;
	private String poster_url;
	private String large_poster_url;
	private float score;
	private int score_count;

	private int wanttosee;
	private int is_wanttosee;
	private String release_date;


	public ComingModel(int id, String name, String genre, String intro,
					   String poster_url, String large_poster_url, String release_date,
					   float score, int score_count, int wanttosee, int is_wanttosee) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.intro = intro;
		this.poster_url = poster_url;
		this.large_poster_url = large_poster_url;
		this.release_date = release_date;
		this.score = score;
		this.score_count = score_count;
		this.wanttosee = wanttosee;
		this.is_wanttosee = is_wanttosee;
	}
	public ComingModel() {
		// TODO Auto-generated constructor stub
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPoster_url() {
		return poster_url;
	}
	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}
	public String getLarge_poster_url() {
		return large_poster_url;
	}
	public void setLarge_poster_url(String large_poster_url) {
		this.large_poster_url = large_poster_url;
	}

	public int getWanttosee() {
		return wanttosee;
	}

	public void setWanttosee(int wanttosee) {
		this.wanttosee = wanttosee;
	}

	public int getIs_wanttosee() {
		return is_wanttosee;
	}

	public void setIs_wanttosee(int is_wanttosee) {
		this.is_wanttosee = is_wanttosee;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getScore_count() {
		return score_count;
	}
	public void setScore_count(int score_count) {
		this.score_count = score_count;
	}
	@Override
	public String toString() {
		return "Coming [id=" + id + ", name=" + name + ", genre=" + genre
				+ ", intro=" + intro + ", poster_url=" + poster_url
				+ ", large_poster_url=" + large_poster_url + ", release_date="
				+ release_date + ", score=" + score + ", score_count="
				+ score_count + ", wanttosee=" + wanttosee + ", is_wanttosee="
				+ is_wanttosee + "]";
	}
}
