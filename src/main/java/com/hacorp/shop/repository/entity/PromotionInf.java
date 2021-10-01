package com.hacorp.shop.repository.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "promote_inf")
public class PromotionInf extends Base{

	private Long id;
	private Product product ;
	private PromotionMas promotionMas;
	public PromotionInf() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne( cascade = CascadeType.PERSIST)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne
	@JoinColumn(name = "promote_id", referencedColumnName = "id")
	public PromotionMas getPromotionMas() {
		return promotionMas;
	}
	public void setPromotionMas(PromotionMas promotionMas) {
		this.promotionMas = promotionMas;
	}
	
	
	
}
