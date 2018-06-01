package com.microservices.poc.restfulwebservices.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MicroServicesConfiguration {

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	@Bean
	public AcceptHeaderLocaleContextResolver acceptLocaleResolver(){
		AcceptHeaderLocaleContextResolver localeResolver=new AcceptHeaderLocaleContextResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource resourceBundleMessageSource(){
		ResourceBundleMessageSource bundleMessageSource=new ResourceBundleMessageSource();
		//bundleMessageSource.setBasename("messages");
		return bundleMessageSource;
	}
}
