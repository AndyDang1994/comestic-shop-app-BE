package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PromotionInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long promoteId;
	private String promoteName;
	private String promoteStatus;
	private Date startAplTime;
	private Date endAplTime;
	private Long volume;
	private String type;
	private List<ProductPromoteInfor> productPromoteInfors;
	public PromotionInfor() {
		super();
	}
	public PromotionInfor(Long promoteId, String promoteName, String promoteStatus, Date startAplTime,
			Date endAplTime, Long volume, String type, List<ProductPromoteInfor> productPromoteInfors) {
		super();
		this.promoteId = promoteId;
		this.promoteName = promoteName;
		this.promoteStatus = promoteStatus;
		this.startAplTime = startAplTime;
		this.endAplTime = endAplTime;
		this.volume = volume;
		this.type = type;
		this.productPromoteInfors = productPromoteInfors;
	}
	
	public PromotionInfor(Long promoteId, String promoteName, String promoteStatus, Date startAplTime, Date endAplTime,
			Long volume, String type) {
		super();
		this.promoteId = promoteId;
		this.promoteName = promoteName;
		this.promoteStatus = promoteStatus;
		this.startAplTime = startAplTime;
		this.endAplTime = endAplTime;
		this.volume = volume;
		this.type = type;
	}
	public Long getPromoteId() {
		return promoteId;
	}
	public void setPromoteId(Long promoteId) {
		this.promoteId = promoteId;
	}
	public String getPromoteName() {
		return promoteName;
	}
	public void setPromoteName(String promoteName) {
		this.promoteName = promoteName;
	}
	public String getPromoteStatus() {
		return promoteStatus;
	}
	public void setPromoteStatus(String promoteStatus) {
		this.promoteStatus = promoteStatus;
	}
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getStartAplTime() {
		return startAplTime;
	}
	public void setStartAplTime(Date startAplTime) {
		this.startAplTime = startAplTime;
	}
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getEndAplTime() {
		return endAplTime;
	}
	public void setEndAplTime(Date endAplTime) {
		this.endAplTime = endAplTime;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ProductPromoteInfor> getProductPromoteInfors() {
		return productPromoteInfors;
	}
	public void setProductPromoteInfors(List<ProductPromoteInfor> productPromoteInfors) {
		this.productPromoteInfors = productPromoteInfors;
	}
	

}
