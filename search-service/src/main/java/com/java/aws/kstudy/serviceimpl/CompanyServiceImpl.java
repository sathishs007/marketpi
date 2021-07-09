package com.java.aws.kstudy.serviceimpl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.aws.kstudy.dao.ICompanyDao;
import com.java.aws.kstudy.domain.CompanyVO;
import com.java.aws.kstudy.service.ICompancyService;

@Service
public class CompanyServiceImpl implements ICompancyService{
	private Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	ICompanyDao companyDao;
	

	@Override
	public List<CompanyVO> getCompanyDetails(String companyCode) throws Exception {
		return companyDao.getCompanyDetails(companyCode);
	}

	@Override
	public List<CompanyVO> getAllCompany() throws Exception {
		return companyDao.getAllCompany();
	}

	

}
