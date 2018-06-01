package com.aws.xray.demo.microservicesxraydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.aws.xray.demo.microservicesxraydemo.config.SpringWebInitializer;


@Configuration
@ComponentScan(basePackages = "com.aws")
@Import({SpringWebInitializer.class})
@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
public class MicroservicesxraydemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesxraydemoApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MicroservicesxraydemoApplication.class);
    }
}
