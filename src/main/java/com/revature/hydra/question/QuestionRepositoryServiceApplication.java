package com.revature.hydra.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Question service main class
 */
//@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EntityScan(value = "com.revature.beans")
public class QuestionRepositoryServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuestionRepositoryServiceApplication.class, args);
	}
	
}
