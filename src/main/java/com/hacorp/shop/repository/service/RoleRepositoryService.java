package com.hacorp.shop.repository.service;

import java.util.List;
import java.util.Map;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.repository.entity.Role;

public interface RoleRepositoryService {
	
	public Role getRoleByRoleCode(Map<String, Object> inputParams) throws BaseException;

	public List<Role> getListRoleByRoleCode(Map<String, Object> inputParams) throws BaseException;

}
