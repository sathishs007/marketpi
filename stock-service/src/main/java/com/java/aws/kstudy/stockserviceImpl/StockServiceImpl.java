package com.java.aws.kstudy.stockserviceImpl;

import java.text.SimpleDateFormat;
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
import com.java.aws.kstudy.stockdao.IStockDao;
import com.java.aws.kstudy.stockdomain.StockVO;
import com.java.aws.kstudy.stockservice.IStockService;

@Service
public class StockServiceImpl implements IStockService {
	private Logger LOG = LoggerFactory.getLogger(StockServiceImpl.class);
	@Autowired
	IStockDao stockDao;
	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;
	@Override
	public void addStock(StockVO vo) throws Exception {
		LOG.info("starting to addStock method  in StockServiceImpl");
		vo.setCreatedDate(new Date());
		vo.setUpdatedDate(new Date());
		vo.setIsDelete(false);
		ObjectMapper mapper=new ObjectMapper();
		String message=mapper.writeValueAsString(vo);
		LOG.info("send data to kafka consumer ");
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("stock", message);
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
		
	}
private String getDate() {
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-YYYY");
	return sdf.format(date);
}



@Override
public void deleteStockDetails(String companyCode) throws Exception {
	 stockDao.deleteStockDetails(companyCode);
	
}
}
