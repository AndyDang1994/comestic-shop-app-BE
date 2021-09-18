package com.hacorp.shop.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacorp.shop.core.constant.ResponseOutPut;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.utils.CommonUtil;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JwtAuthenticationEntryPoint() {
		super();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        ResponseOutPut output = new ResponseOutPut("","Access Denied !",false,HttpServletResponse.SC_UNAUTHORIZED);
        try {
			response.getOutputStream().println(CommonUtil.toJson(output));
		} catch (ServiceRuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
