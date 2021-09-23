/**
 * 
 */
package com.hacorp.shop.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hacorp.shop.repository.service.CategoryRepositoryService;
import com.hacorp.shop.repository.service.ProductRepositoryService;
import com.hacorp.shop.repository.service.PromotionInfRepositoryService;
import com.hacorp.shop.repository.service.PromotionMasRepositoryService;
import com.hacorp.shop.repository.service.RoleRepositoryService;
import com.hacorp.shop.repository.service.SubCategoryRepositoryService;
import com.hacorp.shop.repository.service.UserRepositoryService;
import com.hacorp.shop.repository.service.UserRoleRepositoryService;

/**
 * @author shds01
 *
 */
@Service("repositoryManagerService")
public class RepositoryManagerService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRoleRepositoryService userRoleRepositoryService;
	
	@Autowired
	private UserRepositoryService userRepositoryService;
	
	@Autowired
	private RoleRepositoryService roleRepositoryService;
	
	@Autowired
	private CategoryRepositoryService categoryRepositoryService;
	
	@Autowired
	private SubCategoryRepositoryService subCategoryRepositoryService;
	
	@Autowired
	private ProductRepositoryService productRepositoryService;
	
	@Autowired
	private PromotionInfRepositoryService promotionInfRepositoryService;
	
	@Autowired
	private PromotionMasRepositoryService promotionMasRepositoryService;

	public UserRoleRepositoryService getUserRoleRepositoryService() {
		return userRoleRepositoryService;
	}

	public void setUserRoleRepositoryService(
			@Qualifier("userRoleRepositoryService")	UserRoleRepositoryService userRoleRepositoryService) {
		this.userRoleRepositoryService = userRoleRepositoryService;
	}

	public UserRepositoryService getUserRepositoryService() {
		return userRepositoryService;
	}

	public void setUserRepositoryService(
			@Qualifier("userRepositoryService")	UserRepositoryService userRepositoryService) {
		this.userRepositoryService = userRepositoryService;
	}

	public RoleRepositoryService getRoleRepositoryService() {
		return roleRepositoryService;
	}

	public void setRoleRepositoryService(
			@Qualifier("roleRepositoryService")	RoleRepositoryService roleRepositoryService) {
		this.roleRepositoryService = roleRepositoryService;
	}

	public CategoryRepositoryService getCategoryRepositoryService() {
		return categoryRepositoryService;
	}

	public void setCategoryRepositoryService(
			@Qualifier("categoryRepositoryService")	CategoryRepositoryService categoryRepositoryService) {
		this.categoryRepositoryService = categoryRepositoryService;
	}

	public SubCategoryRepositoryService getSubCategoryRepositoryService() {
		return subCategoryRepositoryService;
	}

	public void setSubCategoryRepositoryService(
			@Qualifier("subCategoryRepositoryService")	SubCategoryRepositoryService subCategoryRepositoryService) {
		this.subCategoryRepositoryService = subCategoryRepositoryService;
	}

	public ProductRepositoryService getProductRepositoryService() {
		return productRepositoryService;
	}

	public void setProductRepositoryService(
			@Qualifier("productRepositoryService")	ProductRepositoryService productRepositoryService) {
		this.productRepositoryService = productRepositoryService;
	}

	public PromotionInfRepositoryService getPromotionInfRepositoryService() {
		return promotionInfRepositoryService;
	}

	public void setPromotionInfRepositoryService(
			@Qualifier("promotionInfRepositoryService")	PromotionInfRepositoryService promotionInfRepositoryService) {
		this.promotionInfRepositoryService = promotionInfRepositoryService;
	}

	public PromotionMasRepositoryService getPromotionMasRepositoryService() {
		return promotionMasRepositoryService;
	}

	public void setPromotionMasRepositoryService(
			@Qualifier("promotionMasRepositoryService")	PromotionMasRepositoryService promotionMasRepositoryService) {
		this.promotionMasRepositoryService = promotionMasRepositoryService;
	}
	
}
