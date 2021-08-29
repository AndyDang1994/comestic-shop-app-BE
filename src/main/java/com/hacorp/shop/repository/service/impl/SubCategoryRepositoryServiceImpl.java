package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.SubCategoryMeta;
import com.hacorp.shop.repository.dao.SubCategoryDAO;
import com.hacorp.shop.repository.entity.SubCategory;
import com.hacorp.shop.repository.service.SubCategoryRepositoryService;

@Service("subCategoryRepositoryService")
public class SubCategoryRepositoryServiceImpl extends AbstractServiceClass implements SubCategoryRepositoryService {

	private SubCategoryDAO objectEntity;
	
	
	@Autowired
	public SubCategoryRepositoryServiceImpl(SubCategoryDAO objectEntity) {
		super();
		this.objectEntity = objectEntity;
	}

	@Override
	public SubCategory getListBySubCode(Map<String, Object> inputParams) throws BaseException {
		String subCode = inputParams.get(APIConstant.ID_KEY).toString();
		return objectEntity.findById(subCode).orElse(null);
	}

	@Override
	public List<SubCategoryMeta> getSubCategoryInfor() throws BaseException{
		String sql ="select new com.hacorp.shop.core.model.SubCategoryMeta(s.subCategoryName,s.subCategoryCode) "
				+ "from SubCategory s "
				+ "where s.ledgerStatus = 'NORM' ";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	@Override
	public boolean save(Map<String, Object> inputParams) throws BaseException {
		try {
			SubCategory item = (SubCategory) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectEntity.save(item);
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
			List<SubCategory> item =  (List<SubCategory>) inputParams.get(APIConstant.USER_KEY);
			if(item != null) {
				objectEntity.saveAll(item);
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
			SubCategory item = (SubCategory) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectEntity.save(item);
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
			List<SubCategory> item =  (List<SubCategory>) inputParams.get(APIConstant.USER_KEY);
			if(item != null) {
				objectEntity.saveAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean delete(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public SubCategory getOne(Map<String, Object> inputParams ) throws BaseException {
		
		String id = (String) inputParams.get(APIConstant.ID_KEY);
		
		if(StringUtils.isBlank(id)) {
			throw new ServiceRuntimeException(env.getProperty("MSG_003"));
		}
		
		return objectEntity.findById(id).orElse(null);
	}

}
