package com.Ecommerce.Entity;

public class LoginResponse {
	
	User user;
	boolean login;
	String message;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "LoginResponse [user=" + user + ", login=" + login + ", message=" + message + "]";
	}
	
	
	

}
