package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.util.List;

public class UserInf implements Serializable {
	
	private static final long serialVersionUID = 1;
	
    private String userName;

    private String email;

    private String password;
    
    private List<String> userRoles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}
	
    
}
