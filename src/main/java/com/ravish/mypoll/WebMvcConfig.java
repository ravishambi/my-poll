package com.ravish.mypoll;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("HEAD","OPTIONS","GET","POST","PUT","DELETE","PATCH")
				.allowedHeaders("Authorization","Content-Type","Origin","access-control-allow-origin","X-Requested-With")
				.maxAge(3600);
	}

}
