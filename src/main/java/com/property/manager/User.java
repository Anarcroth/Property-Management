package com.property.manager;

public class User {

	private String username;

	private String password;

	private String role;

	private String id;

	User() {

	}

	User(String username, String password, String role, String id) {

		this.username = username;
		this.password = password;
		this.role = role;
		this.id = id;
	}

}
