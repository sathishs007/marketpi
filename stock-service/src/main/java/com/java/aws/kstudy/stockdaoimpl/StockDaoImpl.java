package com.java.aws.kstudy.stockdaoimpl;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.stockdao.IStockDao;
import com.java.aws.kstudy.stockdomain.StockVO;


@Repository
public class StockDaoImpl implements IStockDao{
	private Logger LOG = LoggerFactory.getLogger(StockDaoImpl.class);
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Override
	public void deleteStockDetails(String companyCode) throws Exception {
		LOG.info("start deleteCompanyDetails method in dao");
	       final  com.mongodb.client.result.UpdateResult result;
	        try {
	            final Query query = new Query();
	            query.addCriteria(Criteria.where("companyCode").is(companyCode));
	            final Update update = new Update();
	            update.set("isDelete", true);
	            update.set("updatedDate", OffsetDateTime.now());
	            result = mongoTemplate.updateFirst(query, update, StockVO.class);
	            if (!result.wasAcknowledged()) {
	                LOG.info("somthing is wrong going to exception");
	                throw new Exception("company deleted is failed");
	            }
	            LOG.info("successfuly excuted the query ");
	        }
	        catch (final Exception e) {
	            LOG.error(e.getMessage());
	            throw new Exception(e.getMessage());
	        }
		
	}
	
	
}
