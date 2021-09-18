package com.hacorp.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hacorp.shop.common.AbstractBasicCommonClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.core.utils.FileStorageUtils;
import com.hacorp.shop.repository.entity.Product;
import com.hacorp.shop.service.ShopApiService;

@Service("shopApiService")
public class ShopApiServiceImpl extends AbstractBasicCommonClass implements ShopApiService {

	

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Product> getProduct(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getProductRepositoryService().getListBySubCategoryCode(inputParams);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<ProductInfor> getAllProducts(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getProductRepositoryService().getAllList(inputParams);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Long getCountAllProducts(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getProductRepositoryService().getCountProduct(inputParams);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Resource getProductImage(Map<String, Object> inputParams) throws BaseException {
		String fileName = inputParams.get(APIConstant.FILENAME_KEY).toString();
		return FileStorageUtils.loadFileAsResource(fileName, env.getProperty(APIConstant.PATH_IMAGES_STORAGE));
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean editPproduct(Map<String, Object> inputParams) throws BaseException {
		Map<String, Object> item = new HashedMap<>();
		item = getProcessManagerService().getValidationManagementService().validateEditProduct(inputParams);
		boolean flag = (boolean) item.get(APIConstant.RESULT_KEY);
		if(flag == false) {
			throw new ServiceRuntimeException(String.format(env.getProperty("MSG_004")), item.get(APIConstant.RESULT_MSG).toString() );
		}
		getRepositoryManagerService().getProductRepositoryService().save(item);
		return getRepositoryManagerService().getProductRepositoryService().update(item);
	
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Product createProduct(Map<String, Object> inputParams) throws BaseException {
		Map<String, Object> item = new HashedMap<>();
		item = getProcessManagerService().getValidationManagementService().validateCreateProduct(inputParams);
		boolean flag = (boolean) item.get(APIConstant.RESULT_KEY);
		if(flag == false) {
			throw new ServiceRuntimeException(String.format(env.getProperty("MSG_004")), item.get(APIConstant.RESULT_MSG).toString() );
		}
		//getRepositoryManagerService().getProductRepositoryService().save(item);
		return getRepositoryManagerService().getProductRepositoryService().save(item);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean deleteProduct(Map<String, Object> inputParams) throws BaseException {
		Map<String, Object> item = new HashedMap<>();
		item = getProcessManagerService().getValidationManagementService().validateDeleteProduct(inputParams);
		boolean flag = (boolean) item.get(APIConstant.RESULT_KEY);
		if(flag == false) {
			throw new ServiceRuntimeException(item.get(APIConstant.RESULT_MSG).toString());
		}
		return getRepositoryManagerService().getProductRepositoryService().deleteAll(item);
	}

}
