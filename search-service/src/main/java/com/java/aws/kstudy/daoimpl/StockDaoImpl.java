package com.java.aws.kstudy.daoimpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.dao.IStockDao;
import com.java.aws.kstudy.domain.StockVO;
@Repository
public class StockDaoImpl implements IStockDao{
	private Logger LOG = LoggerFactory.getLogger(StockDaoImpl.class);
	@Autowired
    MongoTemplate mongoTemplate;
	@Override
	public StockVO getLatestStock(String companyCode) throws Exception {
		 StockVO vo=null;
		 LOG.info("start getLatestStock method in stockdaoimpl");
		try {
			  Query query=new Query();
		     query.addCriteria(Criteria.where("isDelete").is(false));
			  query.addCriteria(Criteria.where("companyCode").is(companyCode));
			  query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
				
			  vo= mongoTemplate.findOne(query, StockVO.class);
			  LOG.info("end getLatestStock method in stockdaoimpl");
        // query.sort
		}catch (Exception e) {
			LOG.error("error from getLatestStock method "+e.getMessage());
		}
		return vo;
	}
	@Override
	public List<StockVO> getAllLatestStock() throws Exception {
		LOG.info("start getAllLatestStock method in stockdaoimpl");
		List<StockVO>getStockList=null;
		try {
			  Query query=new Query();
		     query.addCriteria(Criteria.where("isDelete").is(false));
			  query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
			  getStockList=  mongoTemplate.find(query, StockVO.class);
			  getStockList= getStockList.stream().filter(distinctByKey(StockVO::getCompanyCode)).collect( Collectors.toList());
			  LOG.info("end getAllLatestStock method in stockdaoimpl" +getStockList.size());
		}catch (Exception e) {
			LOG.error("error from getAllLatestStock method "+e.getMessage());
		}
		return getStockList;
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	@Override
	public List<StockVO> getStackWithStartandEndDate(String companyCode,Date startDate, Date endDate) throws Exception {
		LOG.info("start getAllLatestStock method in stockdaoimpl");
		List<StockVO>getStockList=null;
		try {
			  Query query=new Query();
			  if(companyCode!=null&&!companyCode.isEmpty()) {
				  query.addCriteria(Criteria.where("companyCode").is(companyCode));  
			  }
			  query.addCriteria(Criteria.where("isDelete").is(false));
			  query.addCriteria(Criteria.where("createdDate").gte(startDate));
			  query.addCriteria(Criteria.where("updatedDate").lte(endDate));
			  query.with(Sort.by(Sort.Direction.DESC, "createdDate"));
			  getStockList=  mongoTemplate.find(query, StockVO.class);
		}catch (Exception e) {
			LOG.error("error from getAllLatestStock method "+e.getMessage());
		}
		return getStockList;
	}
}
