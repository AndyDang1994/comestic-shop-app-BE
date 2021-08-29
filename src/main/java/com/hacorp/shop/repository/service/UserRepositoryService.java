package com.hacorp.shop.repository.service;

import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.repository.entity.User;

public interface UserRepositoryService {

	public User getUserByUsername(Map<String, Object> inputParams) throws BaseException;
	
	public boolean save(Map<String, Object> inputParams) throws BaseException;
	
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException;
	
	public boolean update(Map<String, Object> inputParams) throws BaseException;
	
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException;
	
}
