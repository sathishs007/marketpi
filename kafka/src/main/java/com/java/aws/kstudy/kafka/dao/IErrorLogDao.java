package com.java.aws.kstudy.kafka.dao;

import com.java.aws.kstudy.kafka.domain.ErrorLog;

public interface IErrorLogDao {
public void addErrorLog(ErrorLog log) throws Exception;
}
