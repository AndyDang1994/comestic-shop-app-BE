/**
 * 
 */
package com.hacorp.shop.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hacorp.shop.service.AuthenApiService;
import com.hacorp.shop.service.ProductPromotionApiService;
import com.hacorp.shop.service.ShopApiService;


/**
 * @author shds01
 *
 */
@Service("processManagerService")
public class ProcessManagerService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ShopApiService shopApiService;

	@Autowired
	private ValidationManagementService validationManagementService;
	
	@Autowired
	private ValidationPromotionService validationPromotionService;
	
	@Autowired
	private AuthenApiService authenApiService;
	
	@Autowired
	private ProductPromotionApiService productPromotionApiService;
	
	public ShopApiService getShopApiService() {
		return shopApiService;
	}

	public void setShopApiService(
			@Qualifier("shopApiService")	ShopApiService shopApiService) {
		this.shopApiService = shopApiService;
	}

	public ValidationManagementService getValidationManagementService() {
		return validationManagementService;
	}

	public void setValidationManagementService(
			@Qualifier("validationManagementService")	ValidationManagementService validationManagementService) {
		this.validationManagementService = validationManagementService;
	}

	public AuthenApiService getAuthenApiService() {
		return authenApiService;
	}

	public void setAuthenApiService(
			@Qualifier("authenApiService")	AuthenApiService authenApiService) {
		this.authenApiService = authenApiService;
	}

	public ProductPromotionApiService getProductPromotionApiService() {
		return productPromotionApiService;
	}

	public void setProductPromotionApiService(
			@Qualifier("productPromotionApiService")	ProductPromotionApiService productPromotionApiService) {
		this.productPromotionApiService = productPromotionApiService;
	}

	public ValidationPromotionService getValidationPromotionService() {
		return validationPromotionService;
	}

	public void setValidationPromotionService(
			@Qualifier("validationPromotionService")	ValidationPromotionService validationPromotionService) {
		this.validationPromotionService = validationPromotionService;
	}
	
	
}
