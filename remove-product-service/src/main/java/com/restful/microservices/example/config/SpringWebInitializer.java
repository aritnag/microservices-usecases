package com.restful.microservices.example.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class SpringWebInitializer implements WebApplicationInitializer {

	 @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
		 
	              
	        // Filter.
	        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
	 
	        fr.setInitParameter("encoding", "UTF-8");
	        fr.setInitParameter("forceEncoding", "true");
	        fr.addMappingForUrlPatterns(null, true, "/*");
	        
	        //AWS X-Ray Filter
	        FilterRegistration.Dynamic awsXRayFilter = servletContext.addFilter("AWSXRayServletFilter", com.amazonaws.xray.javax.servlet.AWSXRayServletFilter.class);
	   	 
	        awsXRayFilter.setInitParameter("fixedName", "removeproduct-as-a-service-filter");
	        awsXRayFilter.addMappingForUrlPatterns(null, true, "/*");
	    }
}
