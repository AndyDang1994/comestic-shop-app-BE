/**
 * 
 */
package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hacorp.shop.repository.entity.UserRole;

/**
 * @author shds01
 *
 */
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String fullname;
	private String status;
	private String token;
	private Object roles;
	private Object authenFeature;

	/**
	 * 
	 */
	public UserInfo() {
		super();
		this.roles = new ArrayList<>();
		this.authenFeature = new ArrayList<>();
	}

	/**
	 * @param userName
	 * @param password
	 */
	public UserInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @param userName
	 * @param roles
	 */
	public UserInfo(String userName, Object roles) {
		super();
		this.userName = userName;
		this.roles = roles;
	}

	/**
	 * @param userName
	 * @param status
	 * @param roles
	 */
	public UserInfo(String userName, String status, Object roles) {
		super();
		this.userName = userName;
		this.status = status;
		this.roles = roles;
	}

	/**
	 * @param userName
	 * @param fullname
	 * @param status
	 * @param roles
	 */
	public UserInfo(String userName, String fullname, String status, Object roles) {
		super();
		this.userName = userName;
		this.fullname = fullname;
		this.status = status;
		this.roles = roles;
	}

	/**
	 * @param token
	 */
	public UserInfo(String token) {
		super();
		this.token = token;
		this.roles = new ArrayList<UserRole>();
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the roles
	 */
	public Object getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Object roles) {
		this.roles = roles;
	}

	public Object getAuthenFeature() {
		return authenFeature;
	}

	public void setAuthenFeature(Object authenFeature) {
		this.authenFeature = authenFeature;
	}
	
	
}
