package com.hacorp.shop.repository.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "role")
public class Role extends Base {

	private String roleCode;
	private String roleConfig;
	private UserRole userRole;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Role(String roleCode, String roleConfig, UserRole userRole) {
		super();
		this.roleCode = roleCode;
		this.roleConfig = roleConfig;
		this.userRole = userRole;
	}

	@Id
	@Column(name = "role_code")
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Lob
	@Column(name = "role_configure")
	public String getRoleConfig() {
		return roleConfig;
	}
	public void setRoleConfig(String roleConfig) {
		this.roleConfig = roleConfig;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	
}
