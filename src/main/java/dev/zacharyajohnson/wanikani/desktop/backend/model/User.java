package dev.zacharyajohnson.wanikani.desktop.backend.model;

public final class User {
	private String id;
	private String username;
	private int level;
	private String apiKey;
	private boolean loggedIn;

	public User() {

	}

	public User(String id, String username, int level, String apiKey, boolean loggedIn) {
		this.id = id;
		this.username = username;
		this.level = level;
		this.apiKey = apiKey;
		this.loggedIn = loggedIn;
	}

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

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (getLevel() != user.getLevel()) return false;
		if (isLoggedIn() != user.isLoggedIn()) return false;
		if (!getId().equals(user.getId())) return false;
		if (!getUsername().equals(user.getUsername())) return false;
		return getApiKey().equals(user.getApiKey());
	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getUsername().hashCode();
		result = 31 * result + getLevel();
		result = 31 * result + getApiKey().hashCode();
		result = 31 * result + (isLoggedIn() ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", level=" + level +
				", apiKey='" + apiKey + '\'' +
				", isLoggedIn='" + loggedIn + '\'' +
				'}';
	}
}