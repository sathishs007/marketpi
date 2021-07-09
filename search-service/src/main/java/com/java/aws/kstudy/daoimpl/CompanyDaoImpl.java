package com.java.aws.kstudy.daoimpl;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.java.aws.kstudy.dao.ICompanyDao;
import com.java.aws.kstudy.domain.CompanyVO;
import com.mongodb.MongoWriteException;

@Repository
public class CompanyDaoImpl implements ICompanyDao{
	private Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Autowired
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

	@Override
	public List<CompanyVO> getAllCompany() throws Exception {
		LOG.info("start to getCompanyDetails method in CompanyDaoImpl");
		List<CompanyVO>getCompany=null;
		try {
			final Query query = new Query();
            query.addCriteria(Criteria.where("isDelete").is(false));
            getCompany = mongoTemplate.find(query, CompanyVO.class);
		} 
    catch (final Exception e) {
        LOG.error(e.getMessage());
        throw new Exception(e.getMessage());
    }
		LOG.info("end to getCompanyDetails method in CompanyDaoImpl");
		return getCompany;
	}

	@Override
	public void deleteCompanyDetails(String companyCode) throws Exception {
		LOG.info("start deleteCompanyDetails method in dao");
       final  com.mongodb.client.result.UpdateResult result;
        try {
            final Query query = new Query();
            query.addCriteria(Criteria.where("companyCode").is(companyCode));
            // mongoTemplate.find(query, Expense.class);
            final Update update = new Update();
            update.set("isDelete", true);
            update.set("updatedDate", OffsetDateTime.now());
            result = mongoTemplate.updateFirst(query, update, CompanyVO.class);
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
