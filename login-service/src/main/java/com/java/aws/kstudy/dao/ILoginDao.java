package com.java.aws.kstudy.dao;

import com.java.aws.kstudy.model.LoginBO;

public interface ILoginDao {
	public LoginBO validateLogin(String  userName)throws Exception;
}
