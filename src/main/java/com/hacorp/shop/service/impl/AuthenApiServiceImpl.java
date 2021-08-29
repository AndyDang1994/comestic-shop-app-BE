package com.hacorp.shop.service.impl;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hacorp.shop.common.AbstractBasicCommonClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.UnauthorizedException;
import com.hacorp.shop.core.model.MetaDataInfor;
import com.hacorp.shop.core.model.UserInfo;
import com.hacorp.shop.core.utils.CommonUtil;
import com.hacorp.shop.repository.entity.User;
import com.hacorp.shop.service.AuthenApiService;

@Service("authenApiService")
public class AuthenApiServiceImpl extends AbstractBasicCommonClass implements AuthenApiService{

	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@Override
	public UserInfo generateUserToken(Map<String, Object> inputParams, AuthenticationManager authenticationManager) throws BaseException {
		Map<String, Object> userInput = new HashedMap<>();
		UserInfo userInfo = (UserInfo) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), UserInfo.class);
		userInput.put(APIConstant.USERNAME_KEY, userInfo.getUserName());
		User user = getRepositoryManagerService().getUserRepositoryService().getUserByUsername(userInput);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserName(), userInfo.getPassword()));
		} catch (BadCredentialsException e) {
			throw new UnauthorizedException("INVALID_CREDENTIALS", e);
		}

		final String token = jwtTokenUtils.createJWT(userInfo.getUserName());
		userInfo.setToken(token);
		userInfo.setRoles(user.getUserRoles());
		return userInfo;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@Override
	public MetaDataInfor getMetaDataInfor() throws BaseException {
		MetaDataInfor meta = new MetaDataInfor();
		meta.setSubCategoryMeta(getRepositoryManagerService().getSubCategoryRepositoryService().getSubCategoryInfor());
		return meta;
	}

}
