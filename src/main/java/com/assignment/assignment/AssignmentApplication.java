package com.assignment.assignment;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories

@ComponentScan(basePackages = "com.assignment.assignment.*")
@ComponentScan(basePackages = "com.assignment.assignment.dao.*")
@EntityScan(basePackages = "com.assignment.assignment.*")

public class AssignmentApplication {

	 @Bean
	 public ModelMapper modelMapper(){
		 return new ModelMapper();
	 }

	public static void main(String[] args) {

		SpringApplication.run(AssignmentApplication.class, args);
	}

}
