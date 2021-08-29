package com.hacorp.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.repository.entity.Product;

public interface ShopApiService {
	
	public boolean registerUser(Map<String, Object> inputParams) throws BaseException;
	
	public List<Product> getProduct(Map<String, Object> inputParams) throws BaseException;
	
	public Resource getProductImage(Map<String, Object> inputParams) throws BaseException;

	public List<ProductInfor> getAllProducts(Map<String, Object> inputParams) throws BaseException;

	public Long getCountAllProducts(Map<String, Object> inputParams) throws BaseException;

	public boolean editPproduct(Map<String, Object> inputParams) throws BaseException;

	public Product createProduct(Map<String, Object> inputParams) throws BaseException;

	public boolean deleteProduct(Map<String, Object> inputParams) throws BaseException;
	
}
