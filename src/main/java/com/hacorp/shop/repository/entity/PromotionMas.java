package com.hacorp.shop.repository.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "promotion_mas")
public class PromotionMas extends Base{
	
	private Long id;
	private String promoteName;
	private String promoteType;
	private Long volume;
	private Date startAplTime;
	private Date endAplTime;
	private List<PromotionInf> promotionInfs;
	
	public PromotionMas() {
		super();
	}

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "promote_name")
	public String getPromoteName() {
		return promoteName;
	}

	public void setPromoteName(String promoteName) {
		this.promoteName = promoteName;
	}

	@Column(name = "promote_type")
	public String getPromoteType() {
		return promoteType;
	}

	public void setPromoteType(String promoteType) {
		this.promoteType = promoteType;
	}

	@Column(name = "volume")
	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	@Column(name = "start_apl_time")
	public Date getStartAplTime() {
		return startAplTime;
	}

	public void setStartAplTime(Date startAplTime) {
		this.startAplTime = startAplTime;
	}

	@Column(name = "end_apl_time")
	public Date getEndAplTime() {
		return endAplTime;
	}

	public void setEndAplTime(Date endAplTime) {
		this.endAplTime = endAplTime;
	}
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "promotionMas", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<PromotionInf> getPromotionInfs() {
		return promotionInfs;
	}

	public void setPromotionInfs(List<PromotionInf> promotionInfs) {
		this.promotionInfs = promotionInfs;
	}
	
}
