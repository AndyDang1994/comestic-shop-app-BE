/**
 * 
 */
package com.hacorp.shop.common;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;

/**
 * @author shds01
 *
 */
public abstract class AbstractServiceClass extends AbstractRepositoryClass{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public Environment env;
	
	@Autowired
	private ProcessManagerService processManagerService;

//	@Autowired
//    PasswordEncoder passwordEncoder;

	public ProcessManagerService getProcessManagerService() {
		return processManagerService;
	}

	public void setProcessManagerService(
			@Qualifier("processManagerService")	ProcessManagerService processManagerService) {
		this.processManagerService = processManagerService;
	}
	
	

	protected boolean isUserNameExisted(String userName) throws BaseException {
		
		boolean flag =false;
		Map<String, Object> params = new HashedMap<>();
		params.put(APIConstant.USERNAME_KEY, userName);
		if( getRepositoryManagerService().getUserRepositoryService().getUserByUsername(params) != null ) {
			flag = true;
		}
		return flag;
		
	}
	
	protected String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 	return passwordEncoder.encode(password);
	}
	
}







