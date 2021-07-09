package com.java.aws.kstudy.stockservice;

import java.util.Date;
import java.util.List;

import com.java.aws.kstudy.stockdomain.StockVO;


public interface IStockService {
	public void addStock(StockVO vo) throws Exception;
	public void deleteStockDetails(String companyCode) throws Exception;
}
