package com.yi23.rdbms.config;

public class Node {
	private String id;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String trailnumber;

	public void setId(String id) {
		this.id = id;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTrailnumber(String trailnumber) {
		this.trailnumber = trailnumber;
	}

	public String getId() {
		return this.id;
	}

	public String getDriver() {
		return this.driver;
	}

	public String getUrl() {
		return this.url;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getTrailnumber() {
		return this.trailnumber;
	}
}
