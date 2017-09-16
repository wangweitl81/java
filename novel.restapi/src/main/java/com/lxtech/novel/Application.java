package com.lxtech.novel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lxtech.novel.util.ParameterInterceptor;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ParameterInterceptor()).addPathPatterns("/**");
	}
}
