package com.hacorp.shop.repository.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
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
	private LocalDateTime startAplTime;
	private LocalDateTime endAplTime;
	private List<PromotionInf> promotionInfs;
	
	public PromotionMas() {
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
	//@Temporal(TemporalType.TIMESTAMP)
	public LocalDateTime getStartAplTime() {
		return startAplTime;
	}

	public void setStartAplTime(LocalDateTime startAplTime) {
		this.startAplTime = startAplTime;
	}

	@Column(name = "end_apl_time")
	//@Temporal(TemporalType.TIMESTAMP)
	public LocalDateTime getEndAplTime() {
		return endAplTime;
	}

	public void setEndAplTime(LocalDateTime endAplTime) {
		this.endAplTime = endAplTime;
	}
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "promotionMas", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<PromotionInf> getPromotionInfs() {
		return promotionInfs;
	}

	public void setPromotionInfs(List<PromotionInf> promotionInfs) {
		this.promotionInfs = promotionInfs;
	}
	
}
