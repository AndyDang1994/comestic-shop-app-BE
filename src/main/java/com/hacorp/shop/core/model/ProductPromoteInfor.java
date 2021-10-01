package com.hacorp.shop.core.model;

import java.io.Serializable;

public class ProductPromoteInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long promoteId;
	private Long productId;
	private String productName;
	private String promotionType;
	private Long volume;
	private String promoteStatus;
	
	public ProductPromoteInfor() {
		super();
	}

	public ProductPromoteInfor(Long promoteId, Long productId, String promoteStatus) {
		super();
		this.productId = productId;
		this.promoteStatus = promoteStatus;
		this.promoteId = promoteId;
	}

	
	
	public ProductPromoteInfor(Long id, String productName, String promotionType, Long volume,
			String promoteStatus) {
		super();
		this.id = id;
		this.productName = productName;
		this.promotionType = promotionType;
		this.volume = volume;
		this.promoteStatus = promoteStatus;
	}

	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPromoteStatus() {
		return promoteStatus;
	}

	public void setPromoteStatus(String promoteStatus) {
		this.promoteStatus = promoteStatus;
	}

	public Long getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Long promoteId) {
		this.promoteId = promoteId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
