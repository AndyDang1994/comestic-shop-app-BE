package com.hacorp.shop.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.MetaDataInfor;
import com.hacorp.shop.core.model.UserInfo;

@RestController
public class AuthenController extends BaseController{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/api/authentication", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> generateTokenUser(@RequestBody String document, Locale locale) throws Exception {
		Map<String, Object> inputParams = new HashMap<>();
		inputParams.put(APIConstant.DOCUMENT_KEY, document);
		
		UserInfo user = getProcessManagerService().getAuthenApiService().generateUserToken(inputParams,authenticationManager);
		return triggerSuccessOutPut(user, JsonObject.class, null);
	}
	
	@RequestMapping(value = "/api/meta", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getMetaDate( Locale locale) throws BaseException {
		
		MetaDataInfor meta= getProcessManagerService().getAuthenApiService().getMetaDataInfor();
		
		return triggerSuccessOutPut(meta, JsonObject.class, null);
	}
	
}
