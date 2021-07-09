package com.java.aws.kstudy.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/market")
public class TestApi {

	@RequestMapping(value ="/test",method =RequestMethod.GET)
	public void message(HttpServletRequest request) {
		
		System.out.println("welcome" +request.getHeader("Authorisation"));
	}
}
