package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.repository.dao.UserRolesDao;
import com.hacorp.shop.repository.entity.UserRole;
import com.hacorp.shop.repository.service.UserRoleRepositoryService;


@Service("userRoleRepositoryService")
public class UserRoleRepositoryServiceImpl extends AbstractServiceClass implements UserRoleRepositoryService {

	private UserRolesDao entityObject;
	
	
	@Autowired
	public UserRoleRepositoryServiceImpl(UserRolesDao entityObject) {
		this.entityObject = entityObject;
	}

	@Override
	public List<UserRole> getUserRoleByUserName(Map<String, Object> inputParams) throws BaseException {
		try {
			String sql = "select u.* from user_role u "
					+ "where u.user_name = :userName "
					+ "and u.ledger_status = :ledgerStatus";
			Query query = entityManager.createNativeQuery(sql, UserRole.class);
			query.setParameter("userName", inputParams.get(APIConstant.USERNAME_KEY).toString());
			query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
		
	}

	@Override
	public boolean update(Map<String, Object> inputParams) throws BaseException {
		try {
			UserRole item = (UserRole) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			List<UserRole> item = (List<UserRole>) inputParams.get(APIConstant.DOCUMENT_KEY);
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
	public boolean save(Map<String, Object> inputParams) throws BaseException {
		try {
			UserRole item = (UserRole) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			List<UserRole> item = (List<UserRole>) inputParams.get(APIConstant.DOCUMENT_KEY);
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
