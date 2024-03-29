package com.hacorp.shop.repository.service;

import java.util.List;
import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductPromoteInfor;
import com.hacorp.shop.repository.entity.PromotionInf;

public interface PromotionInfRepositoryService {

	public List<PromotionInf> getPromotionMasList(Map<String, Object> inputParams) throws BaseException;
	
	public Long countPromotionMasList(Map<String, Object> inputParams) throws BaseException;
	
	public PromotionInf getOne(Map<String, Object> inputParams) throws BaseException;
	
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException;
	
	public boolean save(Map<String, Object> inputParams) throws BaseException;
	
	public boolean update(Map<String, Object> inputParams) throws BaseException;
	
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException;
	
	public boolean delete(Map<String, Object> inputParams) throws BaseException;
	
	public boolean deleteAll(Map<String, Object> inputParams) throws BaseException;

	public List<ProductPromoteInfor> getProductPromoteList(Map<String, Object> inputParams) throws BaseException;
	
}
