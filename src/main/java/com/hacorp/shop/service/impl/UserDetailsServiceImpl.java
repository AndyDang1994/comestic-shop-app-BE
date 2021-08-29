/**
 * 
 */
package com.hacorp.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hacorp.shop.common.AbstractBasicCommonClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.CustomUserDetails;
import com.hacorp.shop.repository.entity.User;
import com.hacorp.shop.repository.entity.UserRole;


/**
 * @author Kraken
 *
 */
@Service
public class UserDetailsServiceImpl extends AbstractBasicCommonClass implements UserDetailsService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) {
		
		User user = null;
		try {
			Map<String, Object> input = new HashedMap<>();
			input.put(APIConstant.USERNAME_KEY, userName);
			user = getRepositoryManagerService().getUserRepositoryService().getUserByUsername(input);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//userDao.getOne(userName);
		if (user == null || user.getUserName() == null|| StringUtils.isBlank(user.getUserName()) 
				|| user.getStatus().equals(APIConstant.USER_FROZEN)) {
			logger.info("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		
		// [ROLE_USER, ROLE_ADMIN,..]
		
		List<UserRole> roleNames = user.getUserRoles();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if (roleNames != null && roleNames.size() > 0) {
			for (UserRole role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole().getRoleCode());
				grantList.add(authority);
				logger.info("Add role : " + role.getRole().getRoleCode());
			}
		}
		
		return new  CustomUserDetails(user);
	}
	
	
}
