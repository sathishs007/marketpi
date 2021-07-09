package com.java.aws.kstudy.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.aws.kstudy.bo.ResponseObj;
import com.java.aws.kstudy.domain.CompanyVO;
import com.java.aws.kstudy.domain.StockVO;
import com.java.aws.kstudy.service.ICompancyService;
import com.java.aws.kstudy.service.IStockService;



@RestController
@RequestMapping("/api/v1.0/market")
@CrossOrigin(origins = { "*" })
@Validated
public class SearchApi {
	
	
@Autowired
	ICompancyService compancyService;
@Autowired
IStockService stockService;
	private Logger LOG = LoggerFactory.getLogger(SearchApi.class);
	
	
	
	@RequestMapping(value ="/company/info/{companyCode}",method = RequestMethod.GET)
	public ResponseEntity<?> getcompanyDetails(@PathVariable String companyCode,HttpServletRequest request) throws Exception {
		LOG.info("start into getcompanyDetails CompanyApi");
		try {
			if(request.getHeader("Authorisation")==null||request.getHeader("Authorisation").isEmpty()) {
				throw new Exception("invalid user token");
				}
		List<CompanyVO>getCompanyList=compancyService.getCompanyDetails(companyCode);
		StockVO stockVO=stockService.getLatestStock(companyCode);
		ResponseObj obj=new ResponseObj();
		obj.setStockVO(stockVO);
		obj.setGetCompanyList(getCompanyList);
		return new ResponseEntity<>(obj,HttpStatus.OK);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	   
	}
	
	@RequestMapping(value ="/company/getall",method = RequestMethod.GET)
	public ResponseEntity<?> getAllcompanyDetails(HttpServletRequest request) throws Exception {
		LOG.info("start into getAllcompanyDetails CompanyApi");
		try {
			
			  if(request.getHeader("Authorisation")==null||request.getHeader(
			  "Authorisation").isEmpty()) { throw new Exception("invalid user token"); }
			 
		List<CompanyVO>getCompanyList=compancyService.getAllCompany();
		List<StockVO> getStockList=stockService.getAllLatestStock();
		ResponseObj obj=new ResponseObj();
		obj.setGetCompanyList(getCompanyList);
		obj.setGetStockList(getStockList);
		return new ResponseEntity<>(obj,HttpStatus.CREATED);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	   
	}

	
	@RequestMapping(value ="/stock/get/{companyCode}/{startDate}/{endDate}",method = RequestMethod.GET)
	public ResponseEntity<?> getcompanyDetailsWithStartAndEndDate(@PathVariable(name="companyCode",required = true)@NotBlank(message ="companyCode is required") String companyCode,@PathVariable(name="startDate", required = true) @NotBlank(message = "Start date is required")String startDate,@PathVariable(name="endDate",required = true) @NotBlank(message="end date is required") String  endDate,HttpServletRequest request) throws Exception {
		LOG.info("start into getcompanyDetailsWithStartAndEndDate CompanyApi");
		try {
			
			  if(request.getHeader("Authorisation")==null||request.getHeader(
			  "Authorisation").isEmpty()) { throw new Exception("invalid user token"); }
			 
			List<CompanyVO>getCompanyList=compancyService.getCompanyDetails(companyCode);
		List<StockVO>getStockList=stockService.getStackWithStartandEndDate(companyCode,new SimpleDateFormat("yyyy-MM-dd").parse(startDate),new SimpleDateFormat("yyyy-MM-dd").parse(endDate) );
		ResponseObj obj=new ResponseObj();
		obj.setCompanyCode(getCompanyList.get(0).getCompanyCode());
		obj.setCompanyName(getCompanyList.get(0).getCompanyName());
		obj.setGetStockList(getStockList);
		if(getStockList!=null&&getStockList.size()>0) {
		DoubleSummaryStatistics getSum=getmax(getStockList);
	    obj.setSummaryStatistics(getSum);
		}
		return new ResponseEntity<>(obj,HttpStatus.CREATED);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	   
	}
	
	private DoubleSummaryStatistics getmax(List<StockVO>getStockList) {
		return   getStockList.stream()
			    .collect(Collectors.summarizingDouble(StockVO::getStockPrice));
			  //System.out.println("Empoyee salary statistics : " + salaryStatistics);
	}
	
	
	
	
	
}
