package dev.zacharyajohnson.wanikani.desktop.model;

public final class User {
	private String id;
	private String username;
	private int level;
	private String apiKey;
	private boolean isLoggedIn;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}

	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", userName='" + username + '\'' +
				", level=" + level +
				", apiKey='" + apiKey + '\'' +
				", isLoggedIn='" + isLoggedIn + '\'' +
				'}';
	}
}