package com.java.aws.kstudy.dao;

import java.util.Date;
import java.util.List;

import com.java.aws.kstudy.domain.StockVO;

public interface IStockDao {
	public StockVO getLatestStock(String companyCode)throws Exception;
	public List<StockVO> getAllLatestStock()throws Exception;
	public List<StockVO> getStackWithStartandEndDate(String companyCode,Date startDate,Date endDate)throws Exception;
}
