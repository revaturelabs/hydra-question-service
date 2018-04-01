package com.revature.hydra.test.util;

import javax.servlet.ServletContext;

import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
 
@Configuration
public class TestContext {
 
	/*@Bean
	public QuestionCompositionService questionCompositionService() {
		return Mockito.mock(QuestionCompositionService.class);
	}*/
	
	/*@Bean
	public SimpleQuestionRepository simpleQuestionRepository() {
		return Mockito.mock(SimpleQuestionRepository.class);
	}*/
	
	@Autowired
	ServletContext context;    
	
	@Bean
	public ServletContext getServletContext() {
	  return context;
	}
	
	@Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
       RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
       // add properties here
       return mapping;
    }
	
	@Bean
	public AmqpTemplate amqpTemplate() {
		return Mockito.mock(AmqpTemplate.class);
	}
    
}
