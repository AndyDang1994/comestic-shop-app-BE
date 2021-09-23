package com.hacorp.shop.controllers;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;

public class PromotionServiceController extends BaseController{

	@RequestMapping(value = "/api/promotion",produces = "application/json;charset=utf-8", method = RequestMethod.POST )
	public ResponseEntity<Object> createPromotioin(
			@RequestBody String document, Locale locale) throws BaseException {
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
	
}
