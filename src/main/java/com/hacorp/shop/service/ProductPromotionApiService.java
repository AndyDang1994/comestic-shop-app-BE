package com.hacorp.shop.service;

import java.util.List;
import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductPromoteInfor;
import com.hacorp.shop.core.model.PromotionInfor;

public interface ProductPromotionApiService {
	
	public List<PromotionInfor> getListPromotionInfor(Map<String, Object> inputParams) throws BaseException;
	
	public Long countPromotionInfor(Map<String, Object> inputParams) throws BaseException;
	
	public List<ProductPromoteInfor> getListProductPromotionInfor(Map<String, Object> inputParams) throws BaseException;
	
	public boolean creatPromotion(Map<String, Object> inputParams) throws BaseException;
	
	public boolean updatePromotion(Map<String, Object> inputParams) throws BaseException;
	
	public boolean deletePromotion(Map<String, Object> inputParams) throws BaseException;
	
	public boolean creatProductPromotion(Map<String, Object> inputParams) throws BaseException;
	
	public boolean updateProductPromotion(Map<String, Object> inputParams) throws BaseException;
	
	public boolean deleteProductPromotion(Map<String, Object> inputParams) throws BaseException;

	public Long countProductPromotionInfor(Map<String, Object> inputParams) throws BaseException;

}
