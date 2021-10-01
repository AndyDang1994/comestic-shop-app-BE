package com.hacorp.shop.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.AuthenFeatureInfor;
import com.hacorp.shop.core.utils.CommonUtil;
import com.hacorp.shop.repository.entity.User;
import com.hacorp.shop.repository.entity.UserRole;


@Service("validationUserManagementService")
public class ValidationUserManagementService extends AbstractServiceClass {

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Map<String, Object> validUserToAccessResource(String userName,HttpServletRequest request) throws BaseException{
		
		Map<String, Object> rs = new HashedMap<>();
		Map<String, Object> usInpt = new HashedMap<>();
		usInpt.put(APIConstant.USERNAME_KEY, userName);
		User user = getRepositoryManagerService().getUserRepositoryService().getUserByUsername(usInpt);
		List<UserRole> userRoles = user.getUserRoles();
		String accessUrl = request.getRequestURI();
		String method = request.getMethod();
		
		List<AuthenFeatureInfor> AuthenFeatureInfors = new ArrayList<>();
		
		for (UserRole role : userRoles) {
			AuthenFeatureInfors.addAll( (List< AuthenFeatureInfor>)CommonUtil.toListPojo(role.getRole().getRoleConfig(), AuthenFeatureInfor.class ) );
		}
		
		for (AuthenFeatureInfor authen : AuthenFeatureInfors) {
			
			if(accessUrl.equals(authen.getUrl())) {
				
				switch (method) {
					case APIConstant.GET_METHOD_STR:
						if(authen.getReadYn().equals(APIConstant.YES_KEY)) {
							rs.put(APIConstant.RESULT_KEY, true);
							return rs;
						}else {
							rs.put(APIConstant.RESULT_KEY, false);
							return rs;
						}
					case APIConstant.PATCH_METHOD_STR:
					case APIConstant.POST_METHOD_STR:
						if(authen.getUpdateYn().equals(APIConstant.YES_KEY) || authen.getWriteYn().equals(APIConstant.YES_KEY)) {
							rs.put(APIConstant.RESULT_KEY, true);
							return rs;
						}else {
							rs.put(APIConstant.RESULT_KEY, false);
							return rs;
						}
					case APIConstant.DELETE_METHOD_STR:
						if( authen.getDeleteYn().equals(APIConstant.YES_KEY)) {
							rs.put(APIConstant.RESULT_KEY, true);
							return rs;
						}else {
							rs.put(APIConstant.RESULT_KEY, false);
							return rs;
						}
					default:
						rs.put(APIConstant.RESULT_KEY, false);
						return rs;
				}
				
			}
			
		}
		rs.put(APIConstant.RESULT_KEY, false);
		return rs;
		
	}
	
}
