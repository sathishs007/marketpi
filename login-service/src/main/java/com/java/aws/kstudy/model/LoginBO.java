package com.java.aws.kstudy.model;

public class LoginBO {
	private String token;
public LoginBO(String userName, String password, String role) {
		this.userName=userName;
		this.password=password;
		this.role=role;
		
		
	}
public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
private String userName;
private String password;
private String role;


}
