package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.repository.entity.Category;
import com.hacorp.shop.repository.service.CategoryRepositoryService;


@Service("categoryRepositoryService")
public class CategoryRepositoryServiceImpl extends AbstractServiceClass implements CategoryRepositoryService{

	@Override
	public List<Category> getAll(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
