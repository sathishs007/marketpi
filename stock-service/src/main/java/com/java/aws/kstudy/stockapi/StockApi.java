package com.java.aws.kstudy.stockapi;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.java.aws.kstudy.stockbo.JwtUser;
import com.java.aws.kstudy.stockbo.JwtValidator;
import com.java.aws.kstudy.stockbo.ResponseObj;
import com.java.aws.kstudy.stockdomain.CompanyVO;
import com.java.aws.kstudy.stockdomain.StockVO;
import com.java.aws.kstudy.stockservice.IStockService;


@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(origins = { "*" })
@Validated
public class StockApi {
@Autowired
JwtValidator jwtValidator;
	@Autowired
	DiscoveryClient discoveryClient;
	@Autowired
	IStockService stockService;
	private Logger LOG = LoggerFactory.getLogger(StockApi.class);
	@SuppressWarnings("null")
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	public ResponseEntity<?>addStock( @RequestBody @Valid StockVO vo,HttpServletRequest request) throws Exception{
		
		try {
			if(request.getHeader("Authorisation")==null||request.getHeader("Authorisation").isEmpty()) {
				throw new Exception("invalid user token");
					
				}else {
					 JwtUser jwtUser=jwtValidator.validate(request.getHeader("Authorisation").substring(6));
					vo.setCreateBy(jwtUser.getId());
					vo.setUpdateBy(jwtUser.getId());
					vo.setUserId(jwtUser.getId());
				}
			List<CompanyVO>getCompanyLists=getCompanyDetails(vo.getCompanyCode(),request.getHeader("Authorisation").substring(6));
			if(getCompanyLists==null||getCompanyLists.size()==0) {
				throw new Exception("The company code is invalid "+vo.getCompanyCode());
			}
			stockService.addStock(vo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		
	}
	@RequestMapping(value ="/delete/{companyCode}",method = RequestMethod.GET)
	public ResponseEntity<?> deleteStockDetails(@PathVariable String companyCode,HttpServletRequest request) throws Exception {
		LOG.info("start into deleteStockDetails StockApi");
		try {
			if(request.getHeader("Authorisation")==null||request.getHeader("Authorisation").isEmpty()) {
				throw new Exception("invalid user token");
					
				}
			stockService.deleteStockDetails(companyCode);
		return new ResponseEntity<>("stock deleted successfully",HttpStatus.OK);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
	   
	}
	
	
	private List<CompanyVO>getCompanyDetails(String companyCode,String token) throws Exception{
		List<ServiceInstance> instances=discoveryClient.getInstances("search-service");
		ServiceInstance serviceInstance=instances.get(0);
		try {
		String baseUrl=serviceInstance.getUri().toString();
		baseUrl=baseUrl+"/api/v1.0/market/company/info/"+companyCode;
		 RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		    headers.add("Authorisation", "Token " +token);
		    // create request
		    HttpEntity request = new HttpEntity(headers);
		 ResponseEntity<ResponseObj>getCompanyList  = restTemplate.exchange(baseUrl, HttpMethod.GET,request,ResponseObj.class); 
		List<CompanyVO>getCompanyLists=getCompanyList.getBody().getGetCompanyList();
		if(getCompanyList.getStatusCode()==HttpStatus.OK) {
			return getCompanyLists;
		}else {
			throw new Exception("Get Resttemplate error :::: message "+getCompanyList.getStatusCode().toString()+"error code "+getCompanyList.getStatusCodeValue());
		}
		
		}catch(HttpStatusCodeException he) {
			LOG.error("error "+ he.getResponseBodyAsString());
		throw new Exception(he.getResponseBodyAsString());	
		}
		
	}

}
