package com.java.aws.kstudy.kafka.dao;

import com.java.aws.kstudy.kafka.domain.StockVO;

public interface IStockDao {

	public void addStock(StockVO vo) throws Exception;
}
