package com.weibomovie.model;

public class Log {
	
	private Integer id ;
	private String userName ;  
	private long weibo_id;
	private int is_login;
	private String login_date;
	private String logout_date;
	
	
	public Log(Integer id, String userName , long weibo_id , int is_login , String login_date , String logout_date) {
		super();
		this.id = id;
		this.userName = userName; 
		this.weibo_id = weibo_id;
		this.is_login = is_login;
		this.login_date = login_date;
		this.logout_date = logout_date;
	}
	public Log() { 
	} 
	
	
	public int getIs_login() {
		return is_login;
	}
	public void setIs_login(int is_login) {
		this.is_login = is_login;
	}
	public String getLogin_date() {
		return login_date;
	}
	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}
	public String getLogout_date() {
		return logout_date;
	}
	public void setLogout_date(String logout_date) {
		this.logout_date = logout_date;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", userName=" + userName + ", weibo_id="
				+ weibo_id + ", is_login=" + is_login + ", login_date="
				+ login_date + ", logout_date=" + logout_date + "]";
	}
	public long getWeibo_id() {
		return weibo_id;
	}
	public void setWeibo_id(long weibo_id) {
		this.weibo_id = weibo_id;
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
	
	
	
}
