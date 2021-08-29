/**
 * 
 */
package com.hacorp.shop.common;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;

import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.security.JwtTokenUtils;
/**
 * @author shds01
 *
 */
public abstract class AbstractBasicCommonClass extends AbstractServiceClass{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	public Environment env;
	
	@Autowired
	protected HttpServletRequest httpServletRequest;

	@Autowired
	protected HttpServletResponse httpServletResponse;
	
	@Autowired
	protected JwtTokenUtils jwtTokenUtils;
	
	protected String testException() throws ServiceRuntimeException {
		try {
			String a = "";
			String c = "";
			int b = Integer.parseInt(a) + Integer.parseInt(c);
			return String.valueOf(b);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new ServiceRuntimeException("Errror ", e.getCause());
		}
	}
}
