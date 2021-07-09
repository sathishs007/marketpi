package com.java.aws.kstudy.kafka.dao;

import java.util.List;

import com.java.aws.kstudy.kafka.domain.CompanyVO;

public interface ICompanyDao {

	public CompanyVO registerCompany(CompanyVO vo) throws Exception;
	public List<CompanyVO>getCompanyDetails(String companyCode)throws Exception;
}
