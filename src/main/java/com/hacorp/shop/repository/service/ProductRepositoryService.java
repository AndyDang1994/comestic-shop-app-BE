package com.hacorp.shop.repository.service;

import java.util.List;
import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.repository.entity.Product;

public interface ProductRepositoryService {

	public List<Product> getListBySubCategoryCode(Map<String, Object> inputParams) throws BaseException;
	
	public Product getProduct(Map<String, Object> inputParams) throws BaseException;
	
	public Product save(Map<String, Object> inputParams) throws BaseException;
	
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException;
	
	public boolean update(Map<String, Object> inputParams) throws BaseException;
	
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException;
	
	public boolean delete(Map<String, Object> inputParams) throws BaseException;
	
	public boolean deleteAll(Map<String, Object> inputParams) throws BaseException;

	public List<ProductInfor> getAllList(Map<String, Object> inputParams) throws BaseException;

	public Long getCountProduct(Map<String, Object> inputParams) throws BaseException;
	
}
