package com.hacorp.shop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.UserInfo;


@RestController
public class UserManageController  extends BaseController{
	
	@RequestMapping(value = "/api/userinforlst", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getUserInfor(
		@RequestParam(required = false, defaultValue = "") String userName,
		@RequestParam(required = false, defaultValue = "") String status,
		@RequestParam(required = true, defaultValue = "") String _start,
		@RequestParam(required = true, defaultValue = "") String _number,Locale locale) throws Exception {
		Map<String, Object> inputParams = new HashMap<>();
		
		inputParams.put(APIConstant.USERNAME_KEY, userName);
		inputParams.put(APIConstant.STATUS_KEY, status);
		inputParams.put(APIConstant.START_KEY, _start);
		inputParams.put(APIConstant.NUMBER_KEY, _number);
		
		List<UserInfo> item = getProcessManagerService().getAuthenApiService().getUserInforList(inputParams);
		Long count = getRepositoryManagerService().getUserRepositoryService().getUserInforCount(inputParams);
		return triggerSuccessOutPut(item,count);
	}
	
	@RequestMapping(value = "/api/userinforlst", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> createUser(
		@RequestBody  String document
		,Locale locale) throws Exception {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String, Object> inputParams = new HashMap<>();
		
		inputParams.put(APIConstant.DOCUMENT_KEY, document);
		inputParams.put(APIConstant.OPTBY_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		boolean flag = getProcessManagerService().getAuthenApiService().registerUser(inputParams);
		if(!flag) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
		return triggerSuccessOutPut(flag,JsonObject.class,null);
	}
}
