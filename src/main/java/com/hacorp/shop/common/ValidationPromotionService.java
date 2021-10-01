package com.hacorp.shop.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		PromotionInfor item = (PromotionInfor) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), PromotionInfor.class);
		List<ProductPromoteInfor> productPromoteInfors = item.getProductPromoteInfors();
		
		for (ProductPromoteInfor productPromoteInfor : productPromoteInfors) {
			Long id =  productPromoteInfor.getProductId();
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
			prom.setPromotionMas(promotionMas);
			DtoConvert.setEntityUtility(prom, new Date(), APIConstant.SYSADMIN_STR, new Date(), APIConstant.SYSADMIN_STR);
			promotionInfs.add(prom);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		promotionMas.setPromotionInfs(promotionInfs);
		promotionMas.setEndAplTime(LocalDateTime.parse(item.getEndAplTime(),formatter));
		promotionMas.setStartAplTime(LocalDateTime.parse(item.getStartAplTime(),formatter));
		promotionMas.setPromoteName(item.getPromoteName());
		promotionMas.setPromoteType(item.getType());
		promotionMas.setVolume(item.getVolume());
		DtoConvert.setEntityUtility(promotionMas, new Date(), APIConstant.SYSADMIN_STR, new Date(), APIConstant.SYSADMIN_STR);
		rs.put(APIConstant.RESULT_KEY, true);
		rs.put(APIConstant.DOCUMENT_KEY, promotionMas);
		return rs;
	}
	
	public Map<String, Object> validationDeletePromotion(Map<String, Object> inputParams) throws BaseException{
		Map<String, Object> rs = new HashedMap<>();
		List<PromotionInf> promotionInfs = new ArrayList<>();
		List<PromotionMas> promotionMas = new ArrayList<>();
		Map<String, Object> inputProd = new HashedMap<>();
		String optUserName = inputParams.get(APIConstant.USERNAME_KEY).toString();
		
		List<PromotionInfor> items = (List<PromotionInfor>) CommonUtil.toListPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), PromotionInfor.class);
		
		if(items == null) {
			rs.put(APIConstant.RESULT_KEY, false);
			rs.put(APIConstant.RESULT_MSG, "Input Data Is Invalid");
			return rs;
		}
		
		for (PromotionInfor item : items) {
			Long id =  item.getPromoteId();
			inputProd.put(APIConstant.ID_KEY, id);
			PromotionMas prom = getRepositoryManagerService().getPromotionMasRepositoryService().getOne(inputProd);
			if(prom == null) {
				rs.put(APIConstant.RESULT_KEY, false);
				rs.put(APIConstant.RESULT_MSG, "Product Is Not Existed");
				return rs;
			}
			prom.setLedgerStatus(APIConstant.LEDGER_STATUS_DELE);
			prom.getPromotionInfs().forEach(e->{
				e.setLedgerStatus(APIConstant.LEDGER_STATUS_DELE);
				DtoConvert.setEntityUtility(e, null, null, new Date(), optUserName);
			});
			DtoConvert.setEntityUtility(prom, null, null, new Date(), optUserName);
			promotionMas.add(prom);
		}
		
		rs.put(APIConstant.RESULT_KEY, true);
		rs.put(APIConstant.DOCUMENT_KEY, promotionMas);
		return rs;
	}

}
