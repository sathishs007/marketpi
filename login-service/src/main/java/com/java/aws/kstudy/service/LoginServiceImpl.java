package com.java.aws.kstudy.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.aws.kstudy.dao.ILoginDao;
import com.java.aws.kstudy.jwtgenerator.JwtGenerator;
import com.java.aws.kstudy.model.JwtUser;
import com.java.aws.kstudy.model.LoginBO;
@Service
public class LoginServiceImpl implements ILoginService {
	@Autowired
	ILoginDao loginDao;
	@Autowired
	JwtGenerator jwtGenerator;
	@Override
	public String validateLogin(LoginBO bo) throws Exception {
		LoginBO loginBO=loginDao.validateLogin(bo.getUserName());
		if(loginBO!=null) {
			byte[]base64decodedBytes=Base64.getDecoder().decode(loginBO.getPassword());
			if(bo.getPassword().equals(new String(base64decodedBytes, "utf-8"))) {
				
				  JwtUser jwtUser=new JwtUser(); 
				  jwtUser.setUserName(loginBO.getUserName());
				  jwtUser.setId(Long.parseLong(loginBO.getUserName())); 
				  jwtUser.setRole(loginBO.getRole()); 
				  
				  String token=jwtGenerator.generate(jwtUser);
				 return token;
			}else {
				throw new Exception("password not valid");
			}
		}else {
			throw new Exception("username not valid");
		}
	}

	
}
