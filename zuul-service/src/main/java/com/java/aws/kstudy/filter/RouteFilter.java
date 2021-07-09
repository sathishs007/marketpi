package com.java.aws.kstudy.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.java.aws.kstudy.jwt.security.JwtValidator;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RouteFilter extends ZuulFilter {

    private Logger LOG = LoggerFactory.getLogger(RouteFilter.class);

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
      //RequestContext context = RequestContext.getCurrentContext();
       //authenticate(context);
        LOG.info("Route Filter was triggered ");
        return null;
    }

    public boolean authenticate(RequestContext context) throws ZuulException{
    	 String path=context.getRequest().getRequestURI();
         String paths[]=path.split("/");
         if(!paths[1].equalsIgnoreCase("login-service")) {
        	 HttpServletRequest request = context.getRequest();
             Enumeration<String> headerNames = request.getHeaderNames();
             if (headerNames != null) {
                 while (headerNames.hasMoreElements()) {
                     String name = headerNames.nextElement();
                     String values = request.getHeader(name);
                     System.out.println("name "+name);
                     System.out.println("value "+values);
                     //currentContext.addZuulRequestHeader(name, values);
                 }
             }
        String header = context.getRequest().getHeader("Authorisation");
        if (header == null || !header.startsWith("Token ")) {
            throw new RuntimeException("JWT Token is missing");
        }

        String authenticationToken = header.substring(6);
        JwtValidator validate =new JwtValidator();
       
       if( validate.validate(authenticationToken)==null) {
            context.addZuulResponseHeader("Content-Type","application/json");
            context.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            context.setResponseBody("{ \"message\": \"Invalid or no token specified\", \"errorCode\":"+ HttpStatus.FORBIDDEN.value()+ "}");
            context.setSendZuulResponse(false);
            return false;
        }else {
            //throw new ZuulException(new Exception("Invalid or no token specified"), HttpStatus.FORBIDDEN.value(),"Invalid or no token specified");
            return true;
        }
      }
      return true;
    }
}