package com.java.aws.kstudy.service;

import com.java.aws.kstudy.model.LoginBO;

public interface ILoginService {
public String validateLogin(LoginBO bo)throws Exception;
}
