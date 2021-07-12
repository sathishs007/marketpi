package com.java.aws.kstudy.kafka.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.kafka.dao.IStockDao;
import com.java.aws.kstudy.kafka.domain.StockVO;
@Repository
public class StockDaoImpl implements IStockDao{
	private Logger LOG = LoggerFactory.getLogger(StockDaoImpl.class);
	@Autowired
	 @Qualifier("secondaryMongoTemplate")
    MongoTemplate mongoTemplate;
	@Override
	public void addStock(StockVO vo) throws Exception {
		LOG.info("start registerCompany method in CompanyDaoImpl");
        try {
            mongoTemplate.save(vo);
        }catch(DuplicateKeyException dk) {
        	throw new Exception("the company code already present "+vo.getCompanyCode());
        }catch (final Exception e) {
            LOG.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
		
	}

}
