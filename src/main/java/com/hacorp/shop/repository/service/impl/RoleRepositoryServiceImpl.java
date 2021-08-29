package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.repository.dao.RoleDAO;
import com.hacorp.shop.repository.entity.Role;
import com.hacorp.shop.repository.service.RoleRepositoryService;

@Service("roleRepositoryService")
public class RoleRepositoryServiceImpl extends AbstractServiceClass implements RoleRepositoryService{

	private RoleDAO entityObject;
	
	@Autowired
	public RoleRepositoryServiceImpl(RoleDAO entityObject) {
		super();
		this.entityObject = entityObject;
	}


	@Override
	public Role getRoleByRoleCode(Map<String, Object> inputParams) throws BaseException {
		
		String role = (String) inputParams.get(APIConstant.ROLE_KEY);
		return entityObject.findById(role).orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getListRoleByRoleCode(Map<String, Object> inputParams) throws BaseException {
		
		List<String> roles =  (List<String>) inputParams.get(APIConstant.ROLE_KEY);
		String sql = "select * from role "
				+ "where role_code in :roleCode "
				+ "and ledger_status = :ledgerStatus";
		Query query = entityManager.createNativeQuery(sql, Role.class);
		query.setParameter("roleCode", roles);
		query.setParameter("ledgerStatus", APIConstant.LEDGER_STATUS_NORM);
		return query.getResultList();
	}

}
