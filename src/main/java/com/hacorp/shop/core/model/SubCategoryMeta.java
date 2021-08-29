package com.hacorp.shop.core.model;

import java.io.Serializable;

public class SubCategoryMeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	public SubCategoryMeta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubCategoryMeta(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
