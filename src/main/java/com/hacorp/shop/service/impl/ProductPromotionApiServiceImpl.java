package com.hacorp.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractBasicCommonClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.ProductPromoteInfor;
import com.hacorp.shop.core.model.PromotionInfor;
import com.hacorp.shop.service.ProductPromotionApiService;


@Service("productPromotionApiService")
public class ProductPromotionApiServiceImpl extends AbstractBasicCommonClass implements ProductPromotionApiService {

	@Override
	public List<PromotionInfor> getListPromotionInfor(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getPromotionMasRepositoryService().getPromotionInfors(inputParams);
	}

	@Override
	public Long countPromotionInfor(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getPromotionMasRepositoryService().countPromotionMasList(inputParams);
	}
	
	@Override
	public List<ProductPromoteInfor> getListProductPromotionInfor(Map<String, Object> inputParams)
			throws BaseException {
		return getRepositoryManagerService().getPromotionInfRepositoryService().getProductPromoteList(inputParams);
	}
	
	@Override
	public Long countProductPromotionInfor(Map<String, Object> inputParams) throws BaseException {
		return getRepositoryManagerService().getPromotionInfRepositoryService().countPromotionMasList(inputParams);
	}
	
	@Override
	public boolean updatePromotion(Map<String, Object> inputParams) throws BaseException {
		
		return false;
	}

	@Override
	public boolean deletePromotion(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductPromotion(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean creatPromotion(Map<String, Object> inputParams) throws BaseException {
		Map<String, Object> rs = getProcessManagerService().getValidationPromotionService().validationCreatePromotion(inputParams);
		
		boolean flag = (boolean) rs.get(APIConstant.RESULT_KEY);
		if(!flag) {
			throw new ServiceRuntimeException(rs.get(APIConstant.RESULT_MSG).toString());
		}
		
		return getRepositoryManagerService().getPromotionMasRepositoryService().save(rs);
	}

	@Override
	public boolean creatProductPromotion(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProductPromotion(Map<String, Object> inputParams) throws BaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
