package com.restful.microservices.example.config;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import java.util.Collections;
 
@Configuration
public class XrayConfiguration {
 
    @Bean
    public FilterRegistrationBean awsxRayServletFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new AWSXRayServletFilter("removeproduct-as-a-service-filter"));
        bean.setUrlPatterns(Collections.singleton("/*"));
        return bean;
    }
}