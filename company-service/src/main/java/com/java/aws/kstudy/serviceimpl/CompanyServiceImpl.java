package com.java.aws.kstudy.serviceimpl;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.aws.kstudy.dao.ICompanyDao;
import com.java.aws.kstudy.domain.CompanyVO;
import com.java.aws.kstudy.service.ICompancyService;

@Service
public class CompanyServiceImpl implements ICompancyService{
	private Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	ICompanyDao companyDao;
	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public CompanyVO registerCompany(CompanyVO vo) throws Exception {
		LOG.info("starting to registerCompany method  in CompanyServiceImpl");
		vo.setCreatedDate(new Date());
		vo.setUpdatedDate(new Date());
		vo.setIsDelete(false);
		ObjectMapper mapper=new ObjectMapper();
		String message=mapper.writeValueAsString(vo);
		LOG.info("send data to kafka consumer ");
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("company", message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

		      @Override
		      public void onSuccess(SendResult<String, String> result) {
		        LOG.info(String.format("Sent data     = %s", result.getProducerRecord().value()));
		      }

		      @Override
		      public void onFailure(Throwable ex) {
		        
		        try {
		        	LOG.error("Unable to send data to Kafka", ex);
		        	throw new Exception(ex.getMessage());
		        }catch(Exception e) {
		        }
		      }
		    });
		return vo;
	}

	@Override
	public List<CompanyVO> getCompanyDetails(String companyCode) throws Exception {
		return companyDao.getCompanyDetails(companyCode);
	}

	@Override
	public List<CompanyVO> getAllCompany() throws Exception {
		return companyDao.getAllCompany();
	}

	@Override
	public void deleteCompanyDetails(String companyCode) throws Exception {
		companyDao.deleteCompanyDetails(companyCode);
		
	}

}
