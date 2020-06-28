package com.sneekpeak.mystore.model;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	private String userId = "U" + new Random().nextInt(9999);
	private String username;
	@Id
	private String email;
	private String phone;
	private String password;

	public User() {

	}

	public User(String userId, String username, String email, String phone, String password) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
