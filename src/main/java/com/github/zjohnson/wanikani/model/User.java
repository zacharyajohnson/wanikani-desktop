package com.github.zjohnson.wanikani.model;

public final class User {

	private static final User user = new User();

	private String username;
	private String password;
	private String email;
	private int currentLevel;

	private User() {

	}
	
	public static User getInstance() {
		return user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

}