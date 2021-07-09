package com.java.aws.kstudy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.model.LoginBO;
@Repository
public class LoginDaoImpl implements ILoginDao{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public LoginBO validateLogin(String userName) throws Exception {
		return jdbcTemplate.queryForObject("select * from user where username = ?",new Object[] {userName},(rs,rownum) ->
		(new LoginBO(
                rs.getString("userName"),
                rs.getString("password"),
                rs.getString("role")
              
        ))
		);
		// return null;   
	}

}
