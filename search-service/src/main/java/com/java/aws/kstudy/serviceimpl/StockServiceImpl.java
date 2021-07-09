package com.java.aws.kstudy.serviceimpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.aws.kstudy.dao.IStockDao;
import com.java.aws.kstudy.domain.StockVO;
import com.java.aws.kstudy.service.IStockService;
@Service
public class StockServiceImpl implements IStockService {
	private Logger LOG = LoggerFactory.getLogger(StockServiceImpl.class);
	@Autowired
	IStockDao stockDao;
	
public StockVO getLatestStock(String companyCode) throws Exception {
	return stockDao.getLatestStock(companyCode);
}
@Override
public List<StockVO> getAllLatestStock() throws Exception {
	return stockDao.getAllLatestStock();
}
@Override
public List<StockVO> getStackWithStartandEndDate(String companyCode,Date startDate, Date endDate) throws Exception {
	return stockDao.getStackWithStartandEndDate(companyCode,startDate, endDate);
}
}
