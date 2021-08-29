package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.repository.dao.UserDao;
import com.hacorp.shop.repository.entity.User;
import com.hacorp.shop.repository.service.UserRepositoryService;

@Service("userRepositoryService")
public class UserRepositoryServiceImpl extends AbstractServiceClass implements UserRepositoryService {

	private UserDao entityObject;
	
	@Autowired
	public UserRepositoryServiceImpl(UserDao entityObject) {
		super();
		this.entityObject = entityObject;
	}
	
	@Override
	public User getUserByUsername(Map<String, Object> inputParams) throws BaseException {
		String userName = (String) inputParams.get(APIConstant.USERNAME_KEY);
		return entityObject.findById(userName).orElse(null);
	}

	@Override
	public boolean save(Map<String, Object> inputParams) throws BaseException {
		try {
			//Role role = getRepositoryManagerService().getRoleRepositoryService().getRoleByRoleCode(inputParams);
			User item = (User) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				entityObject.save(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException {
		try {
			//Role role = getRepositoryManagerService().getRoleRepositoryService().getRoleByRoleCode(inputParams);
			List<User> item =  (List<User>) inputParams.get(APIConstant.USER_KEY);
			if(item != null) {
				entityObject.saveAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean update(Map<String, Object> inputParams) throws BaseException {
		try {
			//Role role = getRepositoryManagerService().getRoleRepositoryService().getRoleByRoleCode(inputParams);
			User item = (User) inputParams.get(APIConstant.USER_KEY);
			if(item != null) {
				entityObject.save(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException {
		try {
			//Role role = getRepositoryManagerService().getRoleRepositoryService().getRoleByRoleCode(inputParams);
			List<User> item =  (List<User>) inputParams.get(APIConstant.USER_KEY);
			if(item != null) {
				entityObject.saveAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}
	
	

}
