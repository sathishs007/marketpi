package com.java.aws.kstudy.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.aws.kstudy.kafka.dao.ICompanyDao;
import com.java.aws.kstudy.kafka.dao.IErrorLogDao;
import com.java.aws.kstudy.kafka.dao.IStockDao;
import com.java.aws.kstudy.kafka.domain.CompanyVO;
import com.java.aws.kstudy.kafka.domain.ErrorLog;
import com.java.aws.kstudy.kafka.domain.StockVO;

@Service
public final class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    @Autowired
ICompanyDao dao;
    @Autowired
    IStockDao stockDao;
    @Autowired
    IErrorLogDao errorLog;
    
    @KafkaListener(topics = "company", groupId = "group_id")
    public void consumeCompany(String message) throws Exception {
        logger.info(String.format("$$$$ => Consumed message: %s", message));
        try {
        ObjectMapper mapper=new ObjectMapper();
       CompanyVO vo= mapper.readValue(message, CompanyVO.class);
       dao.registerCompany(vo);
        }catch(Exception e) {
        	ErrorLog log=new ErrorLog();
        	log.setErrorMessage(e.getMessage());
        	log.setData(message);
        	errorLog.addErrorLog(log);
        }
    }
    @KafkaListener(topics = "stock", groupId = "group_id")
    public void consumeStock(String message) throws Exception {
        logger.info(String.format("$$$$ => Consumed message: %s", message));
        try {
        ObjectMapper mapper=new ObjectMapper();
       StockVO vo= mapper.readValue(message, StockVO.class);
       stockDao.addStock(vo);
        }catch(Exception e) {
        	ErrorLog log=new ErrorLog();
        	log.setErrorMessage(e.getMessage());
        	log.setData(message);
        	errorLog.addErrorLog(log);
        }
    }
}