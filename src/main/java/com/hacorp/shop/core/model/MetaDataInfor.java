package com.hacorp.shop.core.model;

import java.io.Serializable;
import java.util.List;

public class MetaDataInfor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SubCategoryMeta> subCategoryMeta;

	public MetaDataInfor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MetaDataInfor(List<SubCategoryMeta> subCategoryMeta) {
		super();
		this.subCategoryMeta = subCategoryMeta;
	}

	public List<SubCategoryMeta> getSubCategoryMeta() {
		return subCategoryMeta;
	}

	public void setSubCategoryMeta(List<SubCategoryMeta> subCategoryMeta) {
		this.subCategoryMeta = subCategoryMeta;
	}
	
	

}
