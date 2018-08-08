package com.ee.utilities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginUser {

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", clientType=" + clientType + "]";
	}

	private String username;
	private String password;
	private String clientType;
	
	public LoginUser() {
		
	}
	
	public LoginUser(String username, String password, String clientType) {
		this.clientType = clientType;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	

}
