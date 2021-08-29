package com.hacorp.shop.core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.core.model.UserInf;
import com.hacorp.shop.repository.entity.Product;
import com.hacorp.shop.repository.entity.Role;
import com.hacorp.shop.repository.entity.SubCategory;
import com.hacorp.shop.repository.entity.User;
import com.hacorp.shop.repository.entity.UserRole;

public class DtoConvert {

	public static void setUserUtility(User user, Date createDt, String createdBy, Date lchgDt, String lchgBy) {
		
		if(createDt != null) {
			user.setRegisDate(createDt);
		}
		if(createdBy != null) {
			user.setRegisBy(createdBy);
		}
		if(lchgDt != null) {
			user.setLchgDate(lchgDt);
		}
		if(lchgBy != null) {
			user.setLchgBy(lchgBy);
		}
		
	}
	
	public static void setProductUtility(Product prod, Date createDt, String createdBy, Date lchgDt, String lchgBy) {
		
		if(createDt != null) {
			prod.setRegisDate(createDt);
		}
		if(createdBy != null) {
			prod.setRegisBy(createdBy);
		}
		if(lchgDt != null) {
			prod.setLchgDate(lchgDt);
		}
		if(lchgBy != null) {
			prod.setLchgBy(lchgBy);
		}
		prod.setLedgerStatus(APIConstant.LEDGER_STATUS_NORM);
		
	}
	
	public static void setUserRoleUtility(UserRole userRole, Date createDt, String createdBy, Date lchgDt, String lchgBy) {
		
		if(createDt != null) {
			userRole.setRegisDate(createDt);
		}
		if(createdBy != null) {
			userRole.setRegisBy(createdBy);
		}
		if(lchgDt != null) {
			userRole.setLchgDate(lchgDt);
		}
		if(lchgBy != null) {
			userRole.setLchgBy(lchgBy);
		}
		
	}
	
	public static List<UserRole> setListUserRole(User user, List<Role> roles, String optBy){
		List<UserRole> rs = new ArrayList<>();
		for (Role role : roles) {
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);
			userRole.setLedgerStatus(APIConstant.LEDGER_STATUS_NORM);
			setUserRoleUtility(userRole, DateUtils.getSystemDate(DateUtils.DATEFORMAT), optBy, DateUtils.getSystemDate(DateUtils.DATEFORMAT), optBy);
			rs.add(userRole);
		}
		return rs;
	}
	public static User getUserFromUserInf(UserInf userInf, String optBy) {
		User user = new User(userInf.getUserName(), userInf.getPassword(), APIConstant.LEDGER_STATUS_NORM, null, null, null, null, null, null);
		setUserUtility(user, DateUtils.getSystemDate(DateUtils.DATEFORMAT), optBy, DateUtils.getSystemDate(DateUtils.DATEFORMAT), optBy);
		
		return user;
	}
	
	public static List<ProductInfor> getListProdInfor(List<Product> items){
		
		List<ProductInfor> rs = new ArrayList<>();
		
		for (Product item : items) {
			ProductInfor prod = new ProductInfor();
			prod.setCommentID(item.getId());
			prod.setDescription(item.getDescription());
			prod.setId(item.getId());
			prod.setName(item.getName());
			prod.setPrice(item.getPrice());
			prod.setQuantity(item.getQuantity());
			prod.setVote(item.getVote());
			prod.setSubCateCode(item.getSubCategory().getSubCategoryCode());
			String[] thumnails = ( item.getThumnail() != null ? item.getThumnail().split(";") : " ".split(";") );
			prod.setThumbnail(thumnails);
			rs.add(prod);
		}
		
		return rs;
		
	}
	
	public static void mapEditProductInf(ProductInfor prodInf, Product prod, SubCategory subCate, String userName) {
		if(!StringUtils.isBlank(prodInf.getDescription())) {
			prod.setDescription(prodInf.getDescription());
		}
		if(!StringUtils.isBlank(prodInf.getName())) {
			prod.setName(prodInf.getName());
		}
		prod.setPrice(prodInf.getPrice());
		if(prodInf.getQuantity() > 0) {
			prod.setQuantity(prodInf.getQuantity());
		}
		String thumbnails = "";
		for (int idx = 0; idx <  prodInf.getThumbnail().length; idx++) {
			if(StringUtils.isBlank(prodInf.getThumbnail()[idx])) {
				break;
			}
			thumbnails += prodInf.getThumbnail()[idx];
			if(idx < prodInf.getThumbnail().length -1) {
				thumbnails += ";";
			}
		}
		if(subCate != null) {
			prod.setSubCategory(subCate);
		}
		if(prodInf.getThumbnail().length > 0) {
			prod.setThumnail(thumbnails);
		}
		prod.setLchgBy(userName);
		prod.setLchgDate(new Date());
	}
	
	public static void mapCreateProduct(ProductInfor prodInf, Product prod, SubCategory subCate, String userName) {
		prod.setDescription(prodInf.getDescription());
		prod.setName(prodInf.getName());
		prod.setPrice(prodInf.getPrice());
		prod.setQuantity(prodInf.getQuantity());
		String thumbnails = "";
		for (int idx = 0; idx <  prodInf.getThumbnail().length; idx++) {
			if(StringUtils.isBlank(prodInf.getThumbnail()[idx])) {
				break;
			}
			thumbnails += prodInf.getThumbnail()[idx];
			if(idx < prodInf.getThumbnail().length -1) {
				thumbnails += ";";
			}
		}
		if(prodInf.getThumbnail().length > 0) {
			prod.setThumnail(thumbnails);
		}
		prod.setSubCategory(subCate);
		setProductUtility(prod,new Date(),userName,new Date(),userName);
	}
	
}