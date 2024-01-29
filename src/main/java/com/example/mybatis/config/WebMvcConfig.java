package com.example.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	// upload 경로 설정
	
	// WebMvcConfigurer에서는 @Value("${}")를 인식하지 못하는 Error가 자주 발생한다.
	// 따라서 application.properties에서 경로를 가져오지 않고
	// 직접 작성하여 설정을 해야 한다.
	
	String uploadPath = "D:\\HMS\\myProject\\java\\fileUpload";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
	}
	
}
