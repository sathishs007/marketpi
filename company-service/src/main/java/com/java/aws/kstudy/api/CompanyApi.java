package com.java.aws.kstudy.api;

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

import com.java.aws.kstudy.bo.JwtUser;
import com.java.aws.kstudy.bo.JwtValidator;
import com.java.aws.kstudy.bo.ResponseObj;
import com.java.aws.kstudy.domain.CompanyVO;
import com.java.aws.kstudy.service.ICompancyService;



@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = { "*" })
@Validated
public class CompanyApi {
	@Autowired
	DiscoveryClient discoveryClient;
@Autowired
	ICompancyService compancyService;
@Autowired
JwtValidator jwtValidator;
	private Logger LOG = LoggerFactory.getLogger(CompanyApi.class);
	
	@RequestMapping(value ="/register",method = RequestMethod.POST)
	public ResponseEntity<Void> registerCompany( @RequestBody @Valid CompanyVO vo,HttpServletRequest request) throws Exception {
		LOG.info("start into registerCompany CompanyApi");
		try {
			if(request.getHeader("Authorisation")==null||request.getHeader("Authorisation").isEmpty()) {
			throw new Exception("invalid user token");
				
			}else {
				
				 JwtUser jwtUser=jwtValidator.validate(request.getHeader("Authorisation").substring(6));
				vo.setCreateBy(jwtUser.getId());
				vo.setUpdateBy(jwtUser.getId());
				vo.setUserId(jwtUser.getId());
			}
			
			LOG.info("start into check  getCompanyDetails CompanyApi");
			
			List<CompanyVO>getCompanyList=getCompanyDetails(vo.getCompanyCode(),request.getHeader("Authorisation").substring(6));
			if(getCompanyList!=null&&getCompanyList.size()>0) {
				throw new Exception("The company code already exists "+vo.getCompanyCode());
			}
			compancyService.registerCompany(vo);
			 return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (final IOException | RuntimeException e) {
            LOG.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	@RequestMapping(value ="/delete/{companyCode}",method = RequestMethod.GET)
	public ResponseEntity<?> deleteCompanyDetails(@PathVariable String companyCode,HttpServletRequest request) throws Exception {
		LOG.info("start into getcompanyDetails CompanyApi");
		try {
			if(request.getHeader("Authorisation")==null||request.getHeader("Authorisation").isEmpty()) {
				throw new Exception("invalid user token");
				}
		compancyService.deleteCompanyDetails(companyCode);
		getStockDetailsDelete(companyCode,request.getHeader("Authorisation").substring(6));
		return new ResponseEntity<>("company deleted successfully",HttpStatus.CREATED);
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
		System.out.println("baseUrl  "+baseUrl);
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
		
		private void getStockDetailsDelete(String companyCode,String token) throws Exception{
			List<ServiceInstance> instances=discoveryClient.getInstances("stock-service");
			ServiceInstance serviceInstance=instances.get(0);
			try {
			String baseUrl=serviceInstance.getUri().toString();
			baseUrl=baseUrl+"/api/v1.0/market/stock/delete/"+companyCode;
			 RestTemplate restTemplate = new RestTemplate();
			    HttpHeaders headers = new HttpHeaders();
			    headers.add("Authorisation", "Token " +token);
			    // create request
			    HttpEntity request = new HttpEntity(headers);
			 ResponseEntity<?>getCompanyList  = restTemplate.exchange(baseUrl, HttpMethod.GET,request,HttpStatus.class); 
			if(!(getCompanyList.getStatusCodeValue()==200)) {
				throw new Exception("Get Resttemplate error :::: message "+getCompanyList.getStatusCode().toString()+"error code "+getCompanyList.getStatusCodeValue());
			}
			}catch(HttpStatusCodeException he) {
				LOG.error("error "+ he.getResponseBodyAsString());
			throw new Exception(he.getResponseBodyAsString());	
			}
		
	}
	
}
