package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class PromotionInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long promoteId;
	private String promoteName;
	private String promoteStatus;
	private String startAplTime;
	private String endAplTime;
	private Long volume;
	private String type;
	private List<ProductPromoteInfor> productPromoteInfors;
	public PromotionInfor() {
		super();
	}
	public PromotionInfor(Long promoteId, String promoteName, String promoteStatus, LocalDateTime startAplTime,
			LocalDateTime endAplTime, Long volume, String type, List<ProductPromoteInfor> productPromoteInfors) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.promoteId = promoteId;
		this.promoteName = promoteName;
		this.promoteStatus = promoteStatus;
		this.startAplTime = startAplTime.format(formatter);
		this.endAplTime = endAplTime.format(formatter);
		this.volume = volume;
		this.type = type;
		this.productPromoteInfors = productPromoteInfors;
	}
	
	public PromotionInfor(Long promoteId, String promoteName, String promoteStatus, LocalDateTime startAplTime, LocalDateTime endAplTime,
			Long volume, String type) {
		super();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.promoteId = promoteId;
		this.promoteName = promoteName;
		this.promoteStatus = promoteStatus;
		this.startAplTime = startAplTime.format(formatter);
		this.endAplTime = endAplTime.format(formatter);
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
	
	public String getStartAplTime() {
		return startAplTime;
	}
	public void setStartAplTime(String startAplTime) {
		this.startAplTime = startAplTime;
	}
	
	public String getEndAplTime() {
		return endAplTime;
	}
	public void setEndAplTime(String endAplTime) {
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
