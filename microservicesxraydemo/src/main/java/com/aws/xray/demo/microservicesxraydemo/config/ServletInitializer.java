package com.aws.xray.demo.microservicesxraydemo.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.aws.xray.demo.microservicesxraydemo.MicroservicesxraydemoApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MicroservicesxraydemoApplication.class);
	}

}
