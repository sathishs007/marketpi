package com.java.aws.kstudy.kafka.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.kafka.dao.ICompanyDao;
import com.java.aws.kstudy.kafka.domain.CompanyVO;

@Repository
public class CompanyDaoImpl implements ICompanyDao{
	private Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Autowired
	@Qualifier("primaryMongoTemplate")
    MongoTemplate mongoTemplate;

	@Override
	public CompanyVO registerCompany(CompanyVO vo) throws Exception {
		LOG.info("start registerCompany method in CompanyDaoImpl");
        try {
            mongoTemplate.save(vo);
        
        
        }catch(DuplicateKeyException dk) {
        	throw new Exception("the company code already present "+vo.getCompanyCode());
        }catch (final Exception e) {
            LOG.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return vo;	
	}

	@Override
	public List<CompanyVO> getCompanyDetails(String companyCode) throws Exception {
		LOG.info("start to getCompanyDetails method in CompanyDaoImpl");
		List<CompanyVO>getCompany=null;
		try {
			final Query query = new Query();
            query.addCriteria(Criteria.where("isDelete").is(false));
            query.addCriteria(Criteria.where("companyCode").is(companyCode));
            getCompany = mongoTemplate.find(query, CompanyVO.class);
		} 
    catch (final Exception e) {
        LOG.error(e.getMessage());
        throw new Exception(e.getMessage());
    }
		LOG.info("end to getCompanyDetails method in CompanyDaoImpl");
		return getCompany;
	}
}
