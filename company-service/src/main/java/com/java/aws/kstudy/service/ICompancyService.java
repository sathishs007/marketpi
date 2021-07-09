package com.java.aws.kstudy.service;

import java.util.List;

import com.java.aws.kstudy.domain.CompanyVO;

public interface ICompancyService {

	public CompanyVO registerCompany(CompanyVO vo) throws Exception;
	public List<CompanyVO>getCompanyDetails(String companyCode)throws Exception;
	public List<CompanyVO>getAllCompany()throws Exception;
	public void deleteCompanyDetails(String companyCode) throws Exception;
}
