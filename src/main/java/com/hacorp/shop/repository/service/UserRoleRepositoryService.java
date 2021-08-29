package com.hacorp.shop.repository.service;

import java.util.List;
import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.repository.entity.UserRole;

public interface UserRoleRepositoryService {

	public List<UserRole> getUserRoleByUserName(Map<String, Object> inputParams) throws BaseException;
	public boolean update(Map<String, Object> inputParams) throws BaseException;
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException;
	public boolean save(Map<String, Object> inputParams) throws BaseException;
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException;
	
}
