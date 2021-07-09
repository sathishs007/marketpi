package com.java.aws.kstudy.bo;

import java.io.Serializable;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import com.java.aws.kstudy.domain.CompanyVO;
import com.java.aws.kstudy.domain.StockVO;

public class ResponseObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7755064970776946880L;
	
	private CompanyVO companyVO;
	private StockVO stockVO;
	private List<CompanyVO>getCompanyList;
	private List<StockVO>getStockList;
	private String message;
	private String companyCode;
	private String companyName;
	DoubleSummaryStatistics summaryStatistics;
	
	public DoubleSummaryStatistics getSummaryStatistics() {
		return summaryStatistics;
	}
	public void setSummaryStatistics(DoubleSummaryStatistics summaryStatistics) {
		this.summaryStatistics = summaryStatistics;
	}
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}
	public List<CompanyVO> getGetCompanyList() {
		return getCompanyList;
	}
	public void setGetCompanyList(List<CompanyVO> getCompanyList) {
		this.getCompanyList = getCompanyList;
	}
	public List<StockVO> getGetStockList() {
		return getStockList;
	}
	public void setGetStockList(List<StockVO> getStockList) {
		this.getStockList = getStockList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	

}
