package com.property.manager.models;

public class User {

	private String username;

	private String fullName;

	private String password;

	private String address;

	private String email;

	private String role;

	private int id;

	public User() {

	}

	public User(String username, String fullName, String password, String address,String email, String role, int id) {

		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.role = role;
		this.id = id;
	}

	public final String getUsername() {

		return username;
	}

	public final String getFullName() {

		return fullName;
	}

	public final String getAddress() {

		return address;
	}

	public final String getEmail(){

		return email;
	}

	public final String getPassword() {

		return password;
	}

	public final String getRole() {

		return role;
	}

	public final int getId() {

		return id;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public void setFullName(String fullName) {

		this.fullName = fullName;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public void setEmail(String email){

		this.email = email;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setRole(String role) {

		this.role = role;
	}

	public void setId(int id) {

		this.id = id;
	}
}
