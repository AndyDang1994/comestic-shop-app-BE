/**
 * 
 */
package com.hacorp.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.hacorp.shop.core.constant.APIConstant;

/**
 * @author shds01
 *
 */
@Configuration
@PropertySources({
		@PropertySource(value = "classpath:/META-INF/properties/system.properties", encoding = APIConstant.UTF_8_CHARSET_TYPE),
		@PropertySource(value = "classpath:/META-INF/properties/error.properties", encoding = APIConstant.UTF_8_CHARSET_TYPE)
		})
@ImportResource("classpath:META-INF/config/oms-repository-storage-queries-context.xml")
public class CustomizationPortBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${server.http.port}")
	private int httpPort;
//	@Value("${server.context.path}")
//	private String contextPath;

	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setPort(httpPort);
		//factory.setContextPath(contextPath);
	}

}
