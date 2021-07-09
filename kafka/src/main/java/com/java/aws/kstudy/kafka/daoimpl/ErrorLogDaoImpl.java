package com.java.aws.kstudy.kafka.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.kafka.dao.IErrorLogDao;
import com.java.aws.kstudy.kafka.domain.ErrorLog;
@Repository
public class ErrorLogDaoImpl implements IErrorLogDao{
	private Logger LOG = LoggerFactory.getLogger(ErrorLogDaoImpl.class);
	@Autowired
    MongoTemplate mongoTemplate;
	@Override
	public void addErrorLog(ErrorLog log) throws Exception {
		LOG.info("start addErrorLog method in ErrorLogDaoImpl");
        try {
            mongoTemplate.save(log);
        
        }catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

}
