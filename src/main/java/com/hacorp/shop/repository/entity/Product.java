package com.hacorp.shop.repository.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "product")
public class Product extends Base {

	private long id;
	private String name;
	private int quantity;
	private String thumbnail;
	private String description;
	private BigDecimal price;
	private int vote;
	private long commentID;
	private SubCategory subCategory;
	private List<PromotionInf> promotionInfs;
	
	
	public Product() {
		super();
	}
	
	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name = "thumbnail")
	public String getThumnail() {
		return thumbnail;
	}
	public void setThumnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "price")
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Column(name = "vote")
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	@Column(name = "comment_id")
	public long getCommentID() {
		return commentID;
	}
	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}
	
	@ManyToOne
	@JoinColumn(name = "sub_category_code")
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<PromotionInf> getPromotionInfs() {
		return promotionInfs;
	}

	public void setPromotionInfs(List<PromotionInf> promotionInfs) {
		this.promotionInfs = promotionInfs;
	}
	
	
}
