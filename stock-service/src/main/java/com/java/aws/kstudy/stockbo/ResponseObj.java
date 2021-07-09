package com.java.aws.kstudy.stockbo;

import java.io.Serializable;
import java.util.List;

import com.java.aws.kstudy.stockdomain.CompanyVO;
import com.java.aws.kstudy.stockdomain.StockVO;



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
	
	
	

}
