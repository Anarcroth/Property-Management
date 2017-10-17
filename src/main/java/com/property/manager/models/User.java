package com.property.manager.models;

public class User {

	private String username;

	private String password;

	private String role;

	private String id;

	User() {

	}

	public User(String username, String role, String id) {

		this.username = username;
		this.role = role;
		this.id = id;
	}

	public final String getUsername() {

		return username;
	}

	public final String getPassword() {

		return password;
	}

	public final String getRole() {

		return role;
	}

	public final String getId() {

		return id;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setRole(String role) {

		this.role = role;
	}

	public void setId(String id) {

		this.id = id;
	}
}
