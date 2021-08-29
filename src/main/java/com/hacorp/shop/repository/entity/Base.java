package com.hacorp.shop.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class Base {

	private Date regisDate;
	private String regisBy;
	private Date lchgDate;
	private String lchgBy;
	private String ledgerStatus;
	
	@Column(name = "regis_dt")
	public Date getRegisDate() {
		return regisDate;
	}
	public void setRegisDate(Date regisDate) {
		this.regisDate = regisDate;
	}
	@Column(name = "regis_by")
	public String getRegisBy() {
		return regisBy;
	}
	public void setRegisBy(String regisBy) {
		this.regisBy = regisBy;
	}
	@Column(name = "lchg_dt")
	public Date getLchgDate() {
		return lchgDate;
	}
	public void setLchgDate(Date lchgDate) {
		this.lchgDate = lchgDate;
	}
	@Column(name = "lchg_by")
	public String getLchgBy() {
		return lchgBy;
	}
	public void setLchgBy(String lchgBy) {
		this.lchgBy = lchgBy;
	}
	@Column(name = "ledger_status")
	public String getLedgerStatus() {
		return ledgerStatus;
	}
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}
	
}
