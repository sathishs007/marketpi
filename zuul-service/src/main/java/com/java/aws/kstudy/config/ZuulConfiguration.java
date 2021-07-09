package com.java.aws.kstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.aws.kstudy.filter.ErrorFilter;
import com.java.aws.kstudy.filter.PostFilter;
import com.java.aws.kstudy.filter.PreFilter;
import com.java.aws.kstudy.filter.RouteFilter;

@Configuration
public class ZuulConfiguration {

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

    @Bean
    public PreFilter preFilter(){
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }

    @Bean
    public RouteFilter routeFilter(){
        return new RouteFilter();
    }
}