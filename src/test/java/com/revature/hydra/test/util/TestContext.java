package com.revature.hydra.test.util;

import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.revature.hydra.question.data.SimpleQuestionRepository;
import com.revature.hydra.question.service.QuestionCompositionService;
 
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
	
	@Bean
	public AmqpTemplate amqpTemplate() {
		return Mockito.mock(AmqpTemplate.class);
	}
    
}
