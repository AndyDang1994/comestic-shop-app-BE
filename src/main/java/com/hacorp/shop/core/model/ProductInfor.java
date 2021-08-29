package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductInfor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String subCateCode;
	private int quantity;
	private String[] thumbnail;
	private String description;
	private BigDecimal price;
	private int vote;
	private long commentID;
	
	public ProductInfor() {
		super();
	}

	public ProductInfor(long id, String name, int quantity, String[] thumnail, String description, BigDecimal price,
			int vote, long commentID, String subCateCode) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.thumbnail = thumnail;
		this.description = description;
		this.price = price;
		this.vote = vote;
		this.commentID = commentID;
		this.subCateCode =subCateCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public String[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public long getCommentID() {
		return commentID;
	}

	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}

	public String getSubCateCode() {
		return subCateCode;
	}

	public void setSubCateCode(String subCateCode) {
		this.subCateCode = subCateCode;
	}
	
	
	
}
