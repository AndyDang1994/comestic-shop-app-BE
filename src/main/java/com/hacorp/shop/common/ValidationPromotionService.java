package com.hacorp.shop.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;

import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductPromoteInfor;
import com.hacorp.shop.core.model.PromotionInfor;
import com.hacorp.shop.core.utils.CommonUtil;
import com.hacorp.shop.core.utils.DtoConvert;
import com.hacorp.shop.repository.entity.Product;
import com.hacorp.shop.repository.entity.PromotionInf;
import com.hacorp.shop.repository.entity.PromotionMas;

@Service("validationPromotionService")
public class ValidationPromotionService extends AbstractServiceClass{
	
	public Map<String, Object> validationCreatePromotion(Map<String, Object> inputParams) throws BaseException{
		Map<String, Object> rs = new HashedMap<>();
		Map<String, Object> inputProd = new HashedMap<>();
		List<PromotionInf> promotionInfs = new ArrayList<>();
		PromotionMas promotionMas = new PromotionMas();
		
		PromotionInfor item = (PromotionInfor) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY), PromotionInfor.class);
		List<ProductPromoteInfor> productPromoteInfors = item.getProductPromoteInfors();
		
		for (ProductPromoteInfor productPromoteInfor : productPromoteInfors) {
			Long id =  productPromoteInfor.getProductInfor().getId();
			inputProd.put(APIConstant.ID_KEY, id);
			Product prod = getRepositoryManagerService().getProductRepositoryService().getProduct(inputProd);
			
			if(prod == null) {
				rs.put(APIConstant.RESULT_KEY, false);
				rs.put(APIConstant.RESULT_MSG, "Product Is Not Existed");
				return rs;
			}
			PromotionInf prom = new PromotionInf();
			prom.setProduct(prod);
			prom.setLedgerStatus(APIConstant.LEDGER_STATUS_NORM);
			DtoConvert.setEntityUtility(prom, new Date(), APIConstant.SYSADMIN_STR, new Date(), APIConstant.SYSADMIN_STR);
		}
		
		promotionMas.setPromotionInfs(promotionInfs);
		promotionMas.setEndAplTime(item.getEndAplTime());
		promotionMas.setStartAplTime(item.getStartAplTime());
		promotionMas.setPromoteName(item.getPromoteName());
		promotionMas.setPromoteType(item.getType());
		promotionMas.setVolume(item.getVolume());
		DtoConvert.setEntityUtility(promotionMas, new Date(), APIConstant.SYSADMIN_STR, new Date(), APIConstant.SYSADMIN_STR);
		rs.put(APIConstant.RESULT_KEY, true);
		rs.put(APIConstant.DOCUMENT_KEY, promotionMas);
		return rs;
	}

}
