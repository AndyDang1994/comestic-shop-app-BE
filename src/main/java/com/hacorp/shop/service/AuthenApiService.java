package com.hacorp.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;

import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.MetaDataInfor;
import com.hacorp.shop.core.model.UserInfo;


public interface AuthenApiService {

	public UserInfo generateUserToken(Map<String, Object> inputParams, AuthenticationManager authenticationManager) throws Exception;

	public MetaDataInfor getMetaDataInfor() throws BaseException;

	public boolean registerUser(Map<String, Object> inputParams) throws BaseException;
	
	public List<UserInfo> getUserInforList(Map<String, Object> inputParams)  throws BaseException;

	public Long getUserInforCount(Map<String, Object> inputParams) throws BaseException;
	
}
