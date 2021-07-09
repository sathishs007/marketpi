package com.java.aws.kstudy.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.aws.kstudy.jwtgenerator.JwtGenerator;
import com.java.aws.kstudy.model.JwtUser;
import com.java.aws.kstudy.model.LoginBO;
import com.java.aws.kstudy.service.ILoginService;

@RestController
@RequestMapping("/api/v1.0/market")
@CrossOrigin(origins = { "*" })
@Validated
public class LoginApi {
	private Logger LOG = LoggerFactory.getLogger(LoginApi.class);
	@Autowired
	JwtGenerator jwtGenerator;
	@Autowired
	ILoginService loginService;
	
	@RequestMapping(value ="/login",method =RequestMethod.POST)
	@CrossOrigin(origins = { "*" })
	public ResponseEntity<?> login(@RequestBody LoginBO bo ) throws Exception {
		
		try{
		String token=loginService.validateLogin(bo);
		bo.setToken(token);
		 return new ResponseEntity<>(bo,HttpStatus.OK);
	}catch (final IOException | RuntimeException e) {
        LOG.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	}

}
