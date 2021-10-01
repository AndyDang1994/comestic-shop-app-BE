package com.hacorp.shop.controllers;

import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.PromotionInfor;


@RestController
public class PromotionServiceController extends BaseController{

	@RequestMapping(value = "/api/promotion",produces = "application/json;charset=utf-8", method = RequestMethod.POST )
	public ResponseEntity<Object> createPromotioin(
			@RequestBody(required = true) String document, Locale locale) throws BaseException {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.DOCUMENT_KEY,document);
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		
		boolean item = getProcessManagerService().getProductPromotionApiService().creatPromotion(input);
		if(!item) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
		return triggerSuccessOutPut(item, JsonObject.class, null);
	}
	
	@RequestMapping(value = "/api/promotion",produces = "application/json;charset=utf-8", method = RequestMethod.DELETE )
	public ResponseEntity<Object> deletePromotioin(
			@RequestBody(required = true) String document, Locale locale) throws BaseException {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.DOCUMENT_KEY,document);
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		
		boolean item = getProcessManagerService().getProductPromotionApiService().deletePromotion(input);
		if(!item) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
		return triggerSuccessOutPut(item, JsonObject.class, null);
	}
	
	@RequestMapping(value = "/api/promotion",produces = "application/json;charset=utf-8", method = RequestMethod.GET )
	public ResponseEntity<Object> getPromotion(
			@RequestParam(required = true) String _startDt,
			@RequestParam(required = true) String _endDt,
			@RequestParam(required = false, defaultValue = "") String _promotionName,
			@RequestParam(required = false, defaultValue = "") String _promoteType,
			@RequestParam(required = false, defaultValue = "") String _ledgerStatus,
			@RequestParam(required = false, defaultValue = "") String _start,
			@RequestParam(required = false, defaultValue = "") String _number,
			Locale locale) throws BaseException {
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		input.put(APIConstant.NUMBER_KEY, _number);
		input.put(APIConstant.START_KEY, _start);
		input.put(APIConstant.PROMOTE_NAME_KEY, _promotionName);
		input.put(APIConstant.PROMOTE_TYPE_KEY, _promoteType);
		input.put(APIConstant.LEDGER_STATUS_KEY, _ledgerStatus);
		input.put(APIConstant.START_DT_KEY, _startDt);
		input.put(APIConstant.END_DT_KEY, _endDt);
		
		List<PromotionInfor> item = getProcessManagerService().getProductPromotionApiService().getListPromotionInfor(input);
		BigInteger count = getProcessManagerService().getProductPromotionApiService().countPromotionInfor(input);
		
		return triggerSuccessOutPut(item, count);
	}
	
}
