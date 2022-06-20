package com.doungbu.serp.web;

import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.doungbu.serp.web.config.SessionListener;
import com.doungbu.serp.web.config.WebSecurityConfig;

@SpringBootApplication
public class DoungbuSerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoungbuSerpApplication.class, args);
	}
	
	//세선 설정
	@Bean
	public HttpSessionListener httpSessionListener(){
	    return new SessionListener();
	 }

}
